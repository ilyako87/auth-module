package org.jakot.modules.authmodule.config.security

import org.jakot.modules.authmodule.config.InitService
import org.jakot.modules.authmodule.db.services.AppPropertyService
import org.jakot.modules.authmodule.http.mapping.AUTH_ADD_USER
import org.jakot.modules.authmodule.http.mapping.UPDATE_USER
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.annotation.PostConstruct


@Configuration
class SecurityConfig(val appPropertyService: AppPropertyService,
                     val initService: InitService,
                     val userDetailService: AuthUserDetailService) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @PostConstruct
    fun checkDbInit() {
        if (!appPropertyService.getProperty("db.status.init-done").toBoolean()) {
            initService.initDb(passwordEncoder())
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailService)
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.antMatchers("/resources")?.anyRequest()
    }

    override fun configure(security: HttpSecurity) {
        security
            .authorizeRequests()
            .anyRequest()
            .denyAll()
//            .authorizeRequests()
//                .antMatchers(UPDATE_USER)
//            .denyAll()
//            .and().logout()
//                .permitAll()
//            .and()
//                .httpBasic()
    }
}