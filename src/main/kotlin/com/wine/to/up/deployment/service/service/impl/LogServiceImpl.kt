package com.wine.to.up.deployment.service.service.impl

import com.wine.to.up.deployment.service.dao.LogRepository
import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.service.LogService
import com.wine.to.up.deployment.service.service.SequenceGeneratorService
import com.wine.to.up.deployment.service.vo.LogVO
import org.springframework.stereotype.Service

@Service
class LogServiceImpl(
        private val logRepository: LogRepository,
        val sequenceGeneratorService: SequenceGeneratorService
) : LogService {
    override fun writeLog(user: String, message: String, templateName: String, templateId: Long): LogVO {
        val log = Log(null, System.currentTimeMillis(), message, user, templateName, templateId)
        log.id = sequenceGeneratorService.generateSequence(Log.SEQUENCE_NAME)
        return entitiesToViews(listOf(logRepository.save(log))).first()
    }

    //return last 30 logs
    override fun logsByTemplate(template: ApplicationTemplate, limit: Int): List<LogVO> {
        val logViews = entitiesToViews(
                logRepository.findAllByTemplateName(template.name)
        )
        return logViews.takeLast(30)
    }

    override fun entitiesToViews(logs: List<Log>): List<LogVO> {
        return logs.map {
            LogVO(it.createdDate, it.user, it.log, it.templateName, it.templateId)
        }
    }
}