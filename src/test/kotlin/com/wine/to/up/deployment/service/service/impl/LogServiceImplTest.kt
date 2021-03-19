package com.wine.to.up.deployment.service.service.impl

import com.nhaarman.mockitokotlin2.*
import com.wine.to.up.deployment.service.dao.LogRepository
import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.entity.Log
import com.wine.to.up.deployment.service.service.SequenceGeneratorService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotNull
import kotlin.test.assertSame

@RunWith(MockitoJUnitRunner::class)
class LogServiceImplTest{
    @Mock
    private lateinit var logRepository: LogRepository

    @Mock
    private lateinit var seqGen : SequenceGeneratorService

    @InjectMocks
    private lateinit var logService: LogServiceImpl

    @Test
    fun writeLog() {
        val testLog = Log(1L, 1L, "null", "null", "null", 1L)

        whenever(logRepository.save(any <Log> ())).thenReturn(testLog)
        val testLogResult  = logService.writeLog("user", "message", "templateName", 1L)
        assertNotNull(testLogResult)
        assertSame(testLogResult.templateName, testLog.templateName)
    }

    @Test
    fun logsByTemplate() {
        val testLog = Log(1L, 1L, "null", "null", "null", 1L)
        val myList = listOf(testLog)
        val template = ApplicationTemplate().apply{
            this.baseBranch = ""
            this.createdBy = ""
            this.dateCreated = 1L
            this.description = ""
            this.environmentVariables = null
            this.id = 1L
            this.name = "null"
            this.memoryLimits = 1L
            this.portMappings = null
            this.volumes = null
        }

        whenever(logRepository.findAllByTemplateName(any())).thenReturn(myList)
        val testLogResult = logService.logsByTemplate(template)
        assertNotNull(testLogResult)
        assertSame(testLogResult[0].templateName,testLog.templateName)
    }
}
