package com.wine.to.up.deployment.service.service.impl

import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository
import com.wine.to.up.deployment.service.entity.ApplicationInstance
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus
import com.wine.to.up.deployment.service.service.ApplicationInstanceService
import com.wine.to.up.deployment.service.service.SequenceGeneratorService
import com.wine.to.up.deployment.service.service.ServiceVersionProvider
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequestWrapper
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import org.springframework.stereotype.Service
import javax.ws.rs.NotFoundException

@Service("applicationInstanceService")
class ApplicationInstanceServiceImpl(
        val applicationInstanceRepository: ApplicationInstanceRepository,
        val sequenceGeneratorService: SequenceGeneratorService,
        val serviceVersionProvider: ServiceVersionProvider
) : ApplicationInstanceService {

    override fun deployInstance(applicationDeployRequestWrapper: ApplicationDeployRequestWrapper): ApplicationInstanceVO {
        val applicationTemplateVO = applicationDeployRequestWrapper.applicationTemplateVO
        val alias = applicationDeployRequestWrapper.alias
        val version = serviceVersionProvider.findFullTagName(applicationDeployRequestWrapper.version, applicationTemplateVO)
        val id = sequenceGeneratorService.generateSequence(ApplicationInstance.SEQUENCE_NAME)
        val entity = ApplicationInstance(id, applicationTemplateVO.name, "${applicationTemplateVO.name}_${id}", applicationTemplateVO.id,
                version, System.currentTimeMillis(), "system", ApplicationInstanceStatus.STARTING, "test url", alias)

        return entitiesToVies(listOf(applicationInstanceRepository.save(entity)), ApplicationInstanceStatus.STARTING).first()
    }

    override fun entitiesToVies(entities: List<ApplicationInstance>, forceStatus: ApplicationInstanceStatus?): List<ApplicationInstanceVO> {
        return entities.map {

            val status = if (forceStatus != null) {
                forceStatus
            }  else {
                ApplicationInstanceStatus.RUNNING
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
                    it.alias
            )
        }
    }

    override fun removeEntitiesByIds(ids: List<Long>) {
        removeEntities(ids.map { applicationInstanceRepository.findById(it).orElseThrow() }) // TODO Response status exception here
    }

    override fun removeEntities(entities: List<ApplicationInstance>) {
        entities.forEach {
            //dockerClientFactory.dockerClient.removeServiceCmd(it.appId)
            applicationInstanceRepository.deleteById(it.id)
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