package com.lylist.silentflowers.infra.filter

import com.lylist.silentflowers.security.GetUsernameOnAuthentication
import com.lylist.silentflowers.security.JwtAuthenticationFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun jwtAuthenticationFilter(
        d1: GetUsernameOnAuthentication
    ): JwtAuthenticationFilter =
        JwtAuthenticationFilter(d1)

    @Bean
    fun jwtAuthenticationFilterRegister(
        jwtAuthenticationFilter: JwtAuthenticationFilter
    ): FilterRegistrationBean<JwtAuthenticationFilter> {
        val registrationBean = FilterRegistrationBean<JwtAuthenticationFilter>()
        registrationBean.filter = jwtAuthenticationFilter
        registrationBean.isEnabled = false
        return registrationBean
    }
}
