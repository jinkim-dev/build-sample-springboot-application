package com.jindev.pipeline

import com.jindev.pipeline.api.build.BuildController
import com.jindev.pipeline.configuration.SwaggerConfiguration
import com.jindev.pipeline.configuration.WebConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
open class JinDevPipelineApplication

fun main(args: Array<String>) {
    runApplication<JinDevPipelineApplication>(*args)
}