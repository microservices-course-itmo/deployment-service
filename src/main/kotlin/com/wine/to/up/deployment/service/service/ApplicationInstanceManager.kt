package com.wine.to.up.deployment.service.service

import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO

interface ApplicationInstanceManager {
    fun startApp( applicationInstanceVO: ApplicationInstanceVO)
    fun stopApp(applicationInstanceVO: ApplicationInstanceVO)
    fun restartApp(applicationInstanceVO: ApplicationInstanceVO)
}