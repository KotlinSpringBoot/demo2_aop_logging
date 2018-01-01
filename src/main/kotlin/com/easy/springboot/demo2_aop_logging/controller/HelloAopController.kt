package com.easy.springboot.demo2_aop_logging.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloAopController {

    @GetMapping("hello")
    fun hello(): World {
        return World(name = "AOP", id = "1002")
    }

    data class World(var name: String, var id: String)

}