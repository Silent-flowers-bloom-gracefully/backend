package com.lylist.silentflowers.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChainConfig(http: HttpSecurity): SecurityFilterChain = http
        .csrf { it.disable() }
        .cors { it.disable() }
        .formLogin { it.disable() }
        .logout { it.disable() }
        .sessionManagement { it.disable() }

        .authorizeHttpRequests {
            it
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
        }

        .build()
}