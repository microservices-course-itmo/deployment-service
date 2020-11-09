package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.entity.ApplicationInstance
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequestWrapper
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO

interface ApplicationInstanceService {
    fun getInstancesByTemplateId(templateId: Long): List<ApplicationInstanceVO>
    fun deployInstance(applicationDeployRequestWrapper: ApplicationDeployRequestWrapper): ApplicationInstanceVO
    fun entitiesToVies(entities: List<ApplicationInstance>): List<ApplicationInstanceVO>
}