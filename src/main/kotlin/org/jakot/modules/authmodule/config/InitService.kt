package org.jakot.modules.authmodule.config

import org.jakot.modules.authmodule.config.appconfig.AppProperties
import org.jakot.modules.authmodule.config.security.allPrivileges
import org.jakot.modules.authmodule.config.security.userPrivileges
import org.jakot.modules.authmodule.db.entity.Credentials
import org.jakot.modules.authmodule.db.entity.Privilege
import org.jakot.modules.authmodule.db.entity.User
import org.jakot.modules.authmodule.db.entity.UserGroup
import org.jakot.modules.authmodule.db.jpa.PrivilegeRepository
import org.jakot.modules.authmodule.db.jpa.UserGroupRepository
import org.jakot.modules.authmodule.db.jpa.UserRepository
import org.jakot.modules.authmodule.db.services.AppPropertyService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class InitService(
    val appPropertyService: AppPropertyService,
    val userRepository: UserRepository,
    val userGroupRepository: UserGroupRepository,
    val privilegeRepository: PrivilegeRepository,
    val appProperties: AppProperties
    ) {

    @Transactional
    fun initDb(passwordEncoder: PasswordEncoder) {
        allPrivileges.forEach { privilegeName ->
            privilegeRepository.save(
                Privilege(
                    name = privilegeName,
                    description = privilegeName,
                    userGroups = setOf()
                )
            )
        }
        userGroupRepository.save(
            UserGroup(
                name = "Users",
                description = "Simple Users",
                users = setOf(),
                privileges = privilegeRepository.findAllByNames(userPrivileges)
            )
        )
        val adminGroup = userGroupRepository.save(
            UserGroup(
                name = "Administrators",
                description = "Administrators group of the system",
                users = setOf(),
                privileges = privilegeRepository.findAll().toSet()
            )
        )
        userRepository.save(
            User(
                login = appProperties.security.userByDefault,
                credentials = Credentials(hash = passwordEncoder.encode(appProperties.security.passwordByDefault)),
                userGroups = setOf(adminGroup)
            )
        )
        appPropertyService.setProperty("db.status.init-done", true)
    }
}

