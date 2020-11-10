package com.wine.to.up.deployment.service.service.impl

import com.github.dockerjava.api.model.*
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository
import com.wine.to.up.deployment.service.entity.ApplicationInstance
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus
import com.wine.to.up.deployment.service.service.ApplicationInstanceService
import com.wine.to.up.deployment.service.service.DockerClientFactory
import com.wine.to.up.deployment.service.service.SequenceGeneratorService
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequestWrapper
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import org.springframework.stereotype.Service
import javax.ws.rs.NotFoundException

@Service("applicationInstanceService")
class ApplicationInstanceServiceImpl(
        val applicationInstanceRepository: ApplicationInstanceRepository,
        val dockerClientFactory: DockerClientFactory,
        val sequenceGeneratorService: SequenceGeneratorService
) : ApplicationInstanceService {

    override fun deployInstance(applicationDeployRequestWrapper: ApplicationDeployRequestWrapper): ApplicationInstanceVO {
        val applicationTemplateVO = applicationDeployRequestWrapper.applicationTemplateVO
        val alias = applicationDeployRequestWrapper.alias
        val version = applicationDeployRequestWrapper.version
        val id = sequenceGeneratorService.generateSequence(ApplicationInstance.SEQUENCE_NAME)
        val entity = ApplicationInstance(id, applicationTemplateVO.name, "${applicationTemplateVO.name}_${id}", applicationTemplateVO.id,
                version, System.currentTimeMillis(), "system", ApplicationInstanceStatus.STARTING, "test url", alias)
        val dockerClient = dockerClientFactory.getDockerClient("", "")
        dockerClient.createServiceCmd(ServiceSpec().withName(entity.appId)
                .withTaskTemplate(TaskSpec()
                        .withContainerSpec(ContainerSpec()
                                .withImage("${applicationTemplateVO.name}:dev_${version}")
                                //.withImage("${applicationTemplateVO.name}:latest")
                                .withEnv(applicationTemplateVO.env.map { "${it.name}: ${it.value}" })
                        )
                )
                .withEndpointSpec(EndpointSpec()
                        .withPorts(applicationTemplateVO.ports.map {
                            PortConfig().withPublishedPort(it.key.toInt()).withTargetPort(it.value.toInt())
                        }))).exec()
        return entitiesToVies(listOf(applicationInstanceRepository.save(entity)), ApplicationInstanceStatus.STARTING).first()
    }

    override fun entitiesToVies(entities: List<ApplicationInstance>, forceStatus: ApplicationInstanceStatus?): List<ApplicationInstanceVO> {
        return entities.map {
            val dockerClient = if (entities.isEmpty()) {
                return emptyList()
            } else {
                dockerClientFactory.getDockerClient("", "")
            }
            val dockerService = dockerClient.inspectServiceCmd(it.appId).exec()
            val dockerTasks = dockerClient.listTasksCmd().withNameFilter(dockerService.spec?.name).exec()
            val status = if (forceStatus != null) {
                forceStatus
            } else if (dockerTasks.any { task -> task.status.state.value == "running" }) {
                ApplicationInstanceStatus.RUNNING
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
                    .build()
        }
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
}