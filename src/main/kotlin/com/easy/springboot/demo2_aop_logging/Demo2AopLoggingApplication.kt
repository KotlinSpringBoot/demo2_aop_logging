package com.easy.springboot.demo2_aop_logging

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class])
class Demo2AopLoggingApplication

fun main(args: Array<String>) {
    runApplication<Demo2AopLoggingApplication>(*args)
}


