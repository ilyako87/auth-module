package org.jakot.modules.authmodule.db.entity

import org.jakot.modules.authmodule.db.USER_GROUP_RELATION_TABLE
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @NotNull(message = "")
    @Size(min=4, message = "Login should be not ")
    val login: String,

    @Email(message = "Email should be valid")
    val email: String = "",

    @NotNull
    val activated: Boolean = true,

    @NotNull
    val blocked: Boolean = false,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    val credentials: Credentials,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = USER_GROUP_RELATION_TABLE)
    val userGroups: Collection<UserGroup>

)