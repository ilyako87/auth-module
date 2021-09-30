package org.jakot.modules.authmodule.db.jpa

import org.jakot.modules.authmodule.db.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByLogin(login: String): User
}