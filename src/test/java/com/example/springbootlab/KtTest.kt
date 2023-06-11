package com.example.springbootlab

import org.junit.jupiter.api.Test

class KotlinController : SpringbootLabApplicationTests() {
    @Test
    fun dev() {
        println(hello())
    }
    fun hello(): String {
        return "Hello, kt!"
    }
}