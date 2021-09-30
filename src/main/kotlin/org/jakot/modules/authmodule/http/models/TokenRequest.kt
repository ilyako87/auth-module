package org.jakot.modules.authmodule.http.models

import javax.validation.constraints.Pattern

class TokenRequest(
    val username: String,
    val password: String,
    @Pattern(regexp = ".*")
    val ipAddress: String
)