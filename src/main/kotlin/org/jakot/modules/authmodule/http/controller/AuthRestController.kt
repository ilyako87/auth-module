package org.jakot.modules.authmodule.http.controller

import org.jakot.modules.authmodule.db.entity.Credentials
import org.jakot.modules.authmodule.db.entity.User
import org.jakot.modules.authmodule.db.jpa.UserGroupRepository
import org.jakot.modules.authmodule.db.jpa.UserRepository
import org.jakot.modules.authmodule.http.mapping.AUTH_ADD_USER
import org.jakot.modules.authmodule.http.mapping.AUTH_CONTROLLER_PATH
import org.jakot.modules.authmodule.http.mapping.AUTH_GET_TOKEN
import org.jakot.modules.authmodule.http.mapping.UPDATE_USER
import org.jakot.modules.authmodule.http.models.AddUserRequest
import org.jakot.modules.authmodule.http.models.TokenRequest
import org.jakot.modules.authmodule.http.models.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping(AUTH_CONTROLLER_PATH)
class AuthRestController(val authenticationManager: AuthenticationManager, val userRepository: UserRepository, val userGroupRepository: UserGroupRepository) {

    @PostMapping(AUTH_ADD_USER)
    @ResponseBody
    fun addUser(@RequestBody userRequest: AddUserRequest): String {
        userRepository.save(
            User(
                login = userRequest.uid,
                email = userRequest.email,
                credentials = Credentials(
                    hash = userRequest.hash
                ),
                userGroups = listOf(userGroupRepository.findByName("Users"))
            )
        )
        return ""
    }

    @GetMapping(UPDATE_USER)
    @ResponseBody
    fun updateUser(): String {
        return "Jo!"
    }

    @PostMapping(AUTH_GET_TOKEN)
    @ResponseBody
    fun getToken(response: HttpServletResponse, @RequestBody tokenRequest: TokenRequest): TokenResponse {
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(tokenRequest.username, tokenRequest.password))
        return if (auth.isAuthenticated) {
            TokenResponse("", Date())
        } else {
            response.status = HttpStatus.UNAUTHORIZED.value()
            TokenResponse("ergewrt", Date())
        }
    }



}