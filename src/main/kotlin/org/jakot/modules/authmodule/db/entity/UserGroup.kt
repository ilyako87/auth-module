package org.jakot.modules.authmodule.db.entity

import org.jakot.modules.authmodule.db.GROUPS_PRIVILEGES_RELATIONS_TABLE
import org.jakot.modules.authmodule.db.USER_GROUP_TABLE
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = USER_GROUP_TABLE)
class UserGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @NotNull(message = "")
    val name: String,

    val description: String,

    @ManyToMany(mappedBy = "userGroups")
    val users: Collection<User>,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = GROUPS_PRIVILEGES_RELATIONS_TABLE)
    val privileges: Collection<Privilege>
)
