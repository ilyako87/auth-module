package org.jakot.modules.authmodule.db.entity

import org.jakot.modules.authmodule.db.PRIVILEGES_TABLE
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = PRIVILEGES_TABLE)
class Privilege(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @NotNull(message = "")
    val name: String,

    val description: String,

    @ManyToMany(mappedBy = "privileges")
    val userGroups: Collection<UserGroup>
)
