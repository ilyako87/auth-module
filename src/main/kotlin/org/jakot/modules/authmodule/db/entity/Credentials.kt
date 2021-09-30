package org.jakot.modules.authmodule.db.entity

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Credentials(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @NotNull
    val hash: String,

    @NotNull
    val expired: Boolean = false,

    @OneToOne(mappedBy = "credentials", fetch = FetchType.LAZY)
    val user: User? = null
)