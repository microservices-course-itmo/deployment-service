package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO

interface ApplicationInstanceService {
    fun getInstancesByTemplateId(templateId: Long): List<ApplicationInstanceVO>
    fun deployInstance(applicationTemplateVO: ApplicationTemplateVO) : ApplicationInstanceVO
}