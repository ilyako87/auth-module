package org.jakot.modules.authmodule.config.appconfig

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConstructorBinding
@ConfigurationProperties(prefix = "module")
class AppProperties(
    @NestedConfigurationProperty
    val security: Security = Security(),
    val nextVal: String = ""
)

@ConstructorBinding
class Security(
    val userByDefault: String = "admin",
    val passwordByDefault: String = "123456",
    val tls: TlsConfig = TlsConfig()
)

class TlsConfig(
    val enabled: Boolean = false
)