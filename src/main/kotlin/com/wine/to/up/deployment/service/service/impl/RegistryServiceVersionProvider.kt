package com.wine.to.up.deployment.service.service.impl

import com.wine.to.up.deployment.service.entity.ApplicationTemplate
import com.wine.to.up.deployment.service.service.ServiceVersionProvider
import com.wine.to.up.deployment.service.service.SettingsService
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


/**
 * @author Alexander Kostyurenko
 */
@Service
class RegistryServiceVersionProvider(
        private val settingsService: SettingsService
) : ServiceVersionProvider {

    val restTemplate = RestTemplate()

    override fun findAllVersions(applicationTemplate: ApplicationTemplate): List<String> {
        return listOf("latest")
    }

    override fun findFullTagName(versionOrBranch: String, applicationTemplate: ApplicationTemplateVO): String {
        return "latest"
    }

    private fun getTagsList(applicationName: String): List<String> {
        return listOf("latest")
    }

    companion object {
        private const val MOCK_REGISTRY_RESPONSE =
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
    }

}