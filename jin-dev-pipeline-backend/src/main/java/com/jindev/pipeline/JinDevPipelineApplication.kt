package com.jindev.pipeline

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class JinDevPipelineApplication

fun main(args: Array<String>) {
    runApplication<JinDevPipelineApplication>(*args)
}