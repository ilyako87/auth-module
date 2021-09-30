package org.jakot.modules.authmodule.config

import lombok.extern.slf4j.Slf4j
import org.springframework.context.ApplicationContextInitializer
import org.springframework.web.context.ConfigurableWebApplicationContext

@Slf4j
class AuthSpringContextInitializer: ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    override fun initialize(applicationContext: ConfigurableWebApplicationContext) {
        //
    }
}