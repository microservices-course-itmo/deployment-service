package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO

/**
 * @author Alexander Kostyurenko
 */
interface ServiceVersionProvider {
    fun findAllVersions(applicationTemplate: ApplicationTemplate): List<String>

    fun findFullTagName(versionOrBranch: String, applicationTemplate: ApplicationTemplateVO): String
}