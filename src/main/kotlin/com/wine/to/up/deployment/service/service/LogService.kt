package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO

interface LogService {
    fun logsByInstances(instances: List<ApplicationInstanceVO>): List<Log>
}