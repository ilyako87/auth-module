package org.jakot.modules.authmodule

import org.jakot.modules.authmodule.config.InitService
import org.jakot.modules.authmodule.config.appconfig.AppProperties
import org.jakot.modules.authmodule.db.jpa.UserRepository
import org.jakot.modules.authmodule.db.services.AppPropertyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ContextConfiguration(classes = [InitService::class, AppPropertyService::class])
@EnableConfigurationProperties(value = [AppProperties::class])
@EnableJpaRepositories(basePackages = ["org.jakot.modules.authmodule.*"])
@EntityScan("org.jakot.modules.authmodule.db.entity")
internal class CommonJpaConfiguration {

    @Autowired
    lateinit var initService: InitService

    @Autowired
    lateinit var userRepository: UserRepository

}