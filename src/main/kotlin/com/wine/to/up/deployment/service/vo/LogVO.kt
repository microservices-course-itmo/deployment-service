package com.wine.to.up.deployment.service.vo

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LogVO(
        val date: Long,
        val user: String,
        val message: String,
        val templateName: String,
        val templateId: Long
) {
}