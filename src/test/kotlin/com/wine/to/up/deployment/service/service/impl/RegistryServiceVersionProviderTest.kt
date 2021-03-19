package com.wine.to.up.deployment.service.service.impl

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.service.SettingsService
import com.wine.to.up.deployment.service.vo.SettingsVO
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.web.client.RestTemplate
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(MockitoJUnitRunner::class)
class RegistryServiceVersionProviderTest {
     var  restTemplateMocked = Mockito.mock(RestTemplate::class.java)

     val settingsServiceMocked = Mockito.mock(SettingsService::class.java)

     @InjectMocks
     private lateinit var versionService: RegistryServiceVersionProvider

     val MOCK_REGISTRY_RESPONSE =
            """
                    {
   "name":"demo-service",
   "tags":[
      "sashenka_0.2.0",
      "metrics_practicum_0.3.1",
      "add-endpoint_0.2.0",
      "api-poc_0.1.1",
      "feature-pom_0.2.3",
      "feature-logback_0.1.1",
      "feature-logback_0.1.2",
      "add-method-for-gateway_0.2.0",
      "use-parent_0.3.0",
      "dev_0.3.1",
      "bugfix-bpp_0.2.0",
      "remove-logstash-filter_0.1.4",
      "wip-26.09.20-services_0.2.0",
      "dev_0.1.1",
      "dev_0.1.2"
   ]
}
            
               """

    @Test
    fun findAllVersions(){
        versionService.restTemplate = restTemplateMocked

        val settingsVO = SettingsVO.builder()
                .versionRegistry("")
                .imageRegistry("")
                .dockerAddress("")
                .build()

        whenever(settingsServiceMocked.settings).thenReturn(settingsVO)
        whenever(restTemplateMocked.getForObject(anyString(), eq(String::class.java))).thenReturn(MOCK_REGISTRY_RESPONSE)


        val template = ApplicationTemplate().apply{
            this.baseBranch = "baseBranch"
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
        val checkVersion = versionService.findAllVersions(template)
        assertNotNull(checkVersion)
        assertEquals("sashenka",checkVersion[0])
    }

    @Test
    fun findFullTagName(){
        val applicationTemplate = ApplicationServiceImpl()
        val template = ApplicationTemplate().apply{
            this.baseBranch = "baseBranch"
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
        val templateVO = applicationTemplate.entityToView(template, null, null, null)
        val checkVersion = versionService.findFullTagName("0.1.0",templateVO)

        assertNotNull(checkVersion)
        assertEquals("baseBranch_0.1.0",checkVersion)
    }
}