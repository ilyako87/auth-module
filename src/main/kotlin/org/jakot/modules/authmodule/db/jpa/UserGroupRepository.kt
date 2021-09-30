package org.jakot.modules.authmodule.db.jpa

import org.jakot.modules.authmodule.db.entity.UserGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserGroupRepository: JpaRepository<UserGroup, Long> {

    fun findByName(name: String): UserGroup
}