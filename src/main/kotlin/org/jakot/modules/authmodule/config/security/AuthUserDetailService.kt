package org.jakot.modules.authmodule.config.security

import org.jakot.modules.authmodule.config.models.AuthUserDetails
import org.jakot.modules.authmodule.db.jpa.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthUserDetailService(
    val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return AuthUserDetails(userRepository.findByLogin(username))
    }
}