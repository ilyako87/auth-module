package org.jakot.modules.authmodule.http.models

data class AddUserRequest(
    val uid: String,
    val hash: String,
    val email: String
)