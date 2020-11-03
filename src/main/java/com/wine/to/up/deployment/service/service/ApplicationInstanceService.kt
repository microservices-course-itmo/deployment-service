package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO

interface ApplicationInstanceService {

    fun getInstancesByTemplateId(templateId: Long): List<ApplicationInstanceVO>
}