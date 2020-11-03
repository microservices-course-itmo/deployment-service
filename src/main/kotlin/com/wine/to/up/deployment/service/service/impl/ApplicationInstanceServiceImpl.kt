package com.wine.to.up.deployment.service.service.impl

import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus
import com.wine.to.up.deployment.service.service.ApplicationInstanceService
import com.wine.to.up.deployment.service.service.DockerClientFactory
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import org.springframework.stereotype.Service

@Service("applicationInstanceService")
class ApplicationInstanceServiceImpl(
        val applicationInstanceRepository: ApplicationInstanceRepository,
        val dockerClientFactory: DockerClientFactory
) : ApplicationInstanceService {

    override fun getInstancesByTemplateId(templateId: Long): List<ApplicationInstanceVO> {
        val entities = applicationInstanceRepository.findByTemplateId(templateId)
        val dockerClient = if (entities.isEmpty()) {
            return emptyList()
        } else {
            dockerClientFactory.getDockerClient("", "")
        }
        return entities.map {
            val dockerService = dockerClient.inspectServiceCmd(it.appId).exec()
            val dockerTasks = dockerClient.listTasksCmd().withNameFilter(dockerService.spec?.name).exec()
            val status = if (dockerTasks.any { task -> task.status.state.value == "running" }) {
                ApplicationInstanceStatus.RUNNING
            } else {
                ApplicationInstanceStatus.STOPPED
            }
            ApplicationInstanceVO.builder()
                    .id(it.id)
                    .templateId(it.templateId)
                    .appId(it.appId)
                    .alias("STUB")
                    .containerId("STUB")
                    .createdBy(it.userCreated)
                    .dateCreated(it.dateCreated)
                    .status(status)
                    .version("STUB")
                    .url(it.url)
                    .build()
        }
    }
}