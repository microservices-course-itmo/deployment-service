package com.wine.to.up.deployment.service.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.TypeFactory
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

    var restTemplate = RestTemplate()

    override fun findAllVersions(applicationTemplate: ApplicationTemplate): List<String> {
        val tags = getTagsList(applicationTemplate.name)
        val baseBranch = applicationTemplate.baseBranch
        val versions = mutableSetOf<String>()
        for (tag in tags) {
            val splitted = tag.split("_")
            val version = splitted.last()
            val branchName = splitted.dropLast(1).joinToString("_")
            if (branchName == baseBranch) {
                versions.add(version)
            } else {
                versions.add(branchName)
            }
        }
        return versions.toList()
    }

    override fun findFullTagName(versionOrBranch: String, applicationTemplate: ApplicationTemplateVO): String {
        if (versionOrBranch.matches(Regex("[0-9]\\.[0-9]\\.[0-9]"))) {
            return "${applicationTemplate.baseBranch}_$versionOrBranch"
        }
        val allTags = getTagsList(applicationTemplate.name)
        val version = allTags.filter {
            it.startsWith(versionOrBranch)
        }.map {
            it.split("_").last()
        }.max()
        return "${versionOrBranch}_${version}"
    }

    private fun getTagsList(applicationName: String): List<String> {
        val settings = settingsService.settings
        val url = "${settings.versionRegistry}/v2/${applicationName}/tags/list"
        val registryAnswer = restTemplate.getForObject(url, String::class.java)
        val mapper = ObjectMapper()
        val map: Map<String, Any> = mapper.readValue(registryAnswer,
                TypeFactory.defaultInstance().constructMapType(HashMap::class.java, String::class.java, Any::class.java)
        )
        return map["tags"] as List<String>
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