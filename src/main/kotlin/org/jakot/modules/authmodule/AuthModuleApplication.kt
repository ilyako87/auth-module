package org.jakot.modules.authmodule

import org.jakot.modules.authmodule.config.appconfig.AppProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableConfigurationProperties(AppProperties::class)
@EnableWebSecurity
@SpringBootApplication
class AuthModuleApplication

fun main(args: Array<String>) {
    runApplication<AuthModuleApplication>(*args)
}
