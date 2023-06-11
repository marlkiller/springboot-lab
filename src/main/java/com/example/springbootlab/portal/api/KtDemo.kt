package com.example.springbootlab.portal.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KotlinController {
    @GetMapping("/kt")
    fun hello(): String {
        return "Hello, kt!"
    }
}