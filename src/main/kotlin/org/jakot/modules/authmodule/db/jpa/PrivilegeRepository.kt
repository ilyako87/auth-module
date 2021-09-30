package org.jakot.modules.authmodule.db.jpa

import org.jakot.modules.authmodule.db.entity.Privilege
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PrivilegeRepository: JpaRepository<Privilege, Long> {

    fun findByName(name: String): Privilege

    @Query("SELECT p FROM Privilege p WHERE p.name in :names")
    fun findAllByNames(names: Collection<String>): List<Privilege>
}