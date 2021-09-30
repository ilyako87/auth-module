package org.jakot.modules.authmodule.config.models

import org.jakot.modules.authmodule.config.security.allPrivileges
import org.jakot.modules.authmodule.db.entity.Credentials
import org.jakot.modules.authmodule.db.entity.Privilege
import org.jakot.modules.authmodule.db.entity.User
import org.jakot.modules.authmodule.db.entity.UserGroup
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AuthUserDetailsTest {

    private lateinit var testAuthUserDetails: AuthUserDetails

    @BeforeEach
    fun prepareTestData() {
        testAuthUserDetails = AuthUserDetails(
            User(
                login = "TestUser",
                email = "test.user@test.mail.com",
                credentials = Credentials(hash = "TestPassword123"),
                userGroups = listOf(UserGroup(
                    name = "TestGroup",
                    description = "TestGroup",
                    users = listOf(),
                    privileges = allPrivileges.map { privilege ->
                        Privilege(name = privilege, description = privilege, userGroups = listOf())
                    }
                ))
            )
        )
    }

    @Test
    fun getAuthoritiesTest() {
        Assertions.assertIterableEquals(
            allPrivileges,
            testAuthUserDetails.authorities.map { authority ->
                authority.authority
            })
    }

    @Test
    fun isAccountNonExpiredTest() {
        Assertions.assertTrue(testAuthUserDetails.isAccountNonExpired)
    }

    @Test
    fun isAccountNonLockedTest() {
        Assertions.assertTrue(testAuthUserDetails.isAccountNonLocked)
    }

    @Test
    fun isCredentialsNonExpiredTest() {
        Assertions.assertTrue(testAuthUserDetails.isCredentialsNonExpired)
    }

    @Test
    fun isEnabledTest() {
        Assertions.assertTrue(testAuthUserDetails.isEnabled)
    }
}