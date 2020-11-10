package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.vo.LogVO

interface LogService {
    fun writeLog(user: String, message: String, templateName: String, templateId: Long): LogVO
    fun logsByTemplate(template: ApplicationTemplate): List<LogVO>
    fun entitiesToViews(logs: List<Log>): List<LogVO>
}