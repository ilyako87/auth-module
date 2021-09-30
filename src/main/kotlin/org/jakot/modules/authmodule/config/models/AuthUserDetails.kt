package org.jakot.modules.authmodule.config.models

import org.jakot.modules.authmodule.db.entity.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthUserDetails(private val user: User): UserDetails {

    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return user.userGroups.map { group -> group.privileges }
            .flatten().map { privilege -> SimpleGrantedAuthority(privilege.name) }
    }

    override fun getPassword(): String {
        return user.credentials.hash
    }

    override fun getUsername(): String {
        return user.login
    }

    override fun isAccountNonExpired(): Boolean {
        return (user.activated && !user.blocked)
    }

    override fun isAccountNonLocked(): Boolean {
        return !user.blocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !user.credentials.expired
    }

    override fun isEnabled(): Boolean {
        return user.activated
    }

}