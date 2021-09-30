package org.jakot.modules.authmodule.db.jpa

import org.jakot.modules.authmodule.db.entity.ModuleProperties
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ModulePropertiesRepository: JpaRepository<ModuleProperties, Long> {
    fun findOneByName(login: String): ModuleProperties?
}