package com.lylist.silentflowers.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun filterChainConfig(http: HttpSecurity): SecurityFilterChain = http
        .csrf { it.disable() }
        .cors { it.configurationSource {
            org.springframework.web.cors.CorsConfiguration().apply {
                allowedOrigins = listOf("*",)
                allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
                allowedHeaders = listOf("Contet-Type", "Authorization", "Cache-Control", "X-Requested-With", "Accept", "Origin", "User-Agent")
            }
        }
        }
        .formLogin { it.disable() }
        .logout { it.disable() }
        .sessionManagement { it.disable() }

        .authorizeHttpRequests {
            it
                .requestMatchers("/bucket/**").authenticated()
                .anyRequest().permitAll()
        }

        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        .build()
}