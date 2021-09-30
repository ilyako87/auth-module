package org.jakot.modules.authmodule.http.models

import java.util.*

data class TokenResponse(
    val authToken: String,
    val tokenInitTime: Date
)
