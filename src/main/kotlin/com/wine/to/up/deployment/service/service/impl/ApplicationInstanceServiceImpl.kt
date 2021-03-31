package com.wine.to.up.deployment.service.service.impl

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.model.*
import com.wine.to.up.commonlib.security.AuthenticationProvider
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository
import com.wine.to.up.deployment.service.entity.ApplicationInstance
import com.wine.to.up.deployment.service.entity.EnvironmentVariable
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus
import com.wine.to.up.deployment.service.service.*
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequestWrapper
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.ws.rs.NotFoundException

@Service("applicationInstanceService")
class ApplicationInstanceServiceImpl(
        val applicationInstanceRepository: ApplicationInstanceRepository,
        val dockerClientFactory: DockerClientFactory,
        val sequenceGeneratorService: SequenceGeneratorService,
        val settingsService: SettingsService,
        val serviceVersionProvider: ServiceVersionProvider
) : ApplicationInstanceService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun saveInstance(applicationInstance: ApplicationInstance): ApplicationInstanceVO {
        return entitiesToVies(listOf(applicationInstanceRepository.save(applicationInstance)), ApplicationInstanceStatus.STARTING).first()
    }

    override fun deployInstance(applicationDeployRequestWrapper: ApplicationDeployRequestWrapper): ApplicationInstanceVO {
        val applicationTemplateVO = applicationDeployRequestWrapper.applicationTemplateVO
        val alias = applicationDeployRequestWrapper.alias
        val version = serviceVersionProvider.findFullTagName(applicationDeployRequestWrapper.version, applicationDeployRequestWrapper.applicationTemplateVO)
        val id = sequenceGeneratorService.generateSequence(ApplicationInstance.SEQUENCE_NAME)
        val appId = if (applicationDeployRequestWrapper.alias.isNullOrBlank()) {
            "${applicationTemplateVO.name}_${id}"
        } else {
            applicationDeployRequestWrapper.alias
        }
        val resources = applicationDeployRequestWrapper.resources
        val entity = ApplicationInstance(id, applicationTemplateVO.name, appId, applicationTemplateVO.id,
                version, System.currentTimeMillis(), AuthenticationProvider.getUser()?.id?.toString() ?: "system",
                ApplicationInstanceStatus.STARTING, "test url", appId, applicationDeployRequestWrapper.attributes, resources)
        val dockerClient = dockerClientFactory.dockerClient

        val environmentVariables = applicationTemplateVO.environmentVariables
        environmentVariables.add(constructInstanceIdEnvVar(applicationDeployRequestWrapper, id))
        environmentVariables.add(constructKafkaEnabledEnvVar(applicationDeployRequestWrapper))

        applicationInstanceRepository.removeAllByAppId(entity.appId)
        removeFromDockerByAppId(dockerClient, entity.appId)


        var taskTemplate = TaskSpec()
                .withContainerSpec(ContainerSpec()
                        .withImage("${getRegistryAddress()}/${applicationTemplateVO.name}:${version}")
                        //.withImage("${applicationTemplateVO.name}:latest")
                        .withEnv(environmentVariables.map { "${it.name}=${it.value}" })
                )
        if(resources != null) {
            var dockerResources = ResourceSpecs()
            if(resources.memoryBytesLimit != null) {
                dockerResources = dockerResources.withMemoryBytes(resources.memoryBytesLimit)
            }
            taskTemplate = taskTemplate.withResources(ResourceRequirements().withLimits(dockerResources))
        }
        dockerClient.createServiceCmd(ServiceSpec()
                .withName(entity.appId)
                .withTaskTemplate(taskTemplate)
                .withEndpointSpec(EndpointSpec()
                        .withPorts(applicationTemplateVO.ports?.map {
                            PortConfig().withPublishedPort(it.key.toInt()).withTargetPort(it.value.toInt())
                        } ?: listOf()))).exec()
        return entitiesToVies(listOf(applicationInstanceRepository.save(entity)), ApplicationInstanceStatus.STARTING).first()
    }

    override fun entitiesToVies(entities: List<ApplicationInstance>, forceStatus: ApplicationInstanceStatus?): List<ApplicationInstanceVO> {
        return entities.map {
            val dockerClient = if (entities.isEmpty()) {
                return emptyList()
            } else {
                dockerClientFactory.dockerClient
            }
            val dockerService = try {
                dockerClient.inspectServiceCmd(it.appId).exec()
            } catch (e: com.github.dockerjava.api.exception.NotFoundException) {
                null
            }
            val dockerTasks = dockerClient?.listTasksCmd()?.withNameFilter(dockerService?.spec?.name)?.exec()
                    ?: emptyList()
            val status = if (forceStatus != null) {
                forceStatus
            } else if (dockerTasks.any { task -> task.status.state.value == "running" }) {
                ApplicationInstanceStatus.RUNNING
            } else if (dockerTasks.isEmpty()) {
                ApplicationInstanceStatus.REMOVED
            } else {
                ApplicationInstanceStatus.STOPPED
            }
            ApplicationInstanceVO.builder()
                    .id(it.id)
                    .templateId(it.templateId)
                    .appId(it.appId)
                    .alias(it.alias)
                    .createdBy(it.userCreated)
                    .dateCreated(it.dateCreated)
                    .status(status)
                    .version(it.version)
                    .url(it.url)
                    .attributes(it.attributes)
                    .resources(it.resources)
                    .build()
        }
    }

    override fun viewsToEntities(views: List<ApplicationInstanceVO>): List<ApplicationInstance> {
        return views.map {
            ApplicationInstance(
                    it.id,
                    it.templateId.toString(), //TODO remove from here
                    it.appId,
                    it.templateId,
                    it.version,
                    it.dateCreated,
                    it.createdBy,
                    it.status,
                    it.url,
                    it.alias,
                    it.attributes,
                    it.resources
            )
        }
    }

    override fun removeEntitiesByIds(ids: List<Long>) {
        removeEntities(ids.map { applicationInstanceRepository.findById(it).orElseThrow { NotFoundException() } })
    }

    override fun removeEntities(entities: List<ApplicationInstance>) {
        entities.forEach {
            removeFromDockerByAppId(dockerClientFactory.dockerClient, it.appId)
            applicationInstanceRepository.deleteById(it.id)
        }
    }

    override fun removeEntityById(id: Long): ApplicationInstanceVO {
        val entity = applicationInstanceRepository.findById(id).orElseThrow { NotFoundException() }
        removeEntities(listOf(entity))
        return entitiesToVies(listOf(entity)).first()
    }

    private fun getRegistryAddress(): String {
        return settingsService.settings.imageRegistry
    }

    override fun getInstancesByTemplateName(templateName: String): List<ApplicationInstanceVO> {
        val entities = applicationInstanceRepository.findByTemplateName(templateName)
        return entitiesToVies(entities)
    }

    override fun getInstancesByTemplateId(templateId: Long): List<ApplicationInstanceVO> {
        val entities = applicationInstanceRepository.findByTemplateId(templateId)
        return entitiesToVies(entities)
    }

    override fun getInstanceById(instanceId: Long): ApplicationInstanceVO {
        return entitiesToVies(listOf(applicationInstanceRepository.findById(instanceId)
                .orElseThrow { NotFoundException() })).first()
    }

    override fun getInstances(): List<com.github.dockerjava.api.model.Service> {

        return dockerClientFactory.dockerClient.listServicesCmd().exec().toList()
    }

    private fun removeFromDockerByAppId(dockerClient: DockerClient, appId: String) {
        try {
            dockerClient.removeServiceCmd(appId).exec()
        } catch (e: com.github.dockerjava.api.exception.NotFoundException) {
            //do nothing
        }
    }

    private fun constructInstanceIdEnvVar(applicationDeployRequestWrapper: ApplicationDeployRequestWrapper,
                                          id: Long): EnvironmentVariable {
        val instanceIdVarName = "INSTANCE_ID"
        val attributes = applicationDeployRequestWrapper.attributes
        var variableValue = "$id"
        if (attributes == null) {
            return EnvironmentVariable(instanceIdVarName, variableValue)
        }
        if (attributes.isTestInstance) {
            variableValue = "test_$variableValue"
        }
        if (attributes.isStopTraffic) {
            variableValue = "stopTraffic_$variableValue"
        }
        return EnvironmentVariable(instanceIdVarName, variableValue)
    }

    private fun constructKafkaEnabledEnvVar(
            applicationDeployRequestWrapper: ApplicationDeployRequestWrapper): EnvironmentVariable {
        val kafkaEnabledVarName = "KAFKA_ENABLED"
        val attributes = applicationDeployRequestWrapper.attributes
                ?: return EnvironmentVariable(kafkaEnabledVarName, true.toString())
        return EnvironmentVariable(kafkaEnabledVarName, (!attributes.isStopTraffic).toString())
    }

}