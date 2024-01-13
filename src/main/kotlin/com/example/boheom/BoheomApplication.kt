package com.example.boheom

import com.example.boheom.global.security.jwt.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(TokenProperties::class)
@SpringBootApplication
class BoheomApplication

fun main(args: Array<String>) {
    runApplication<BoheomApplication>(*args)
}
