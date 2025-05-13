package com.lylist.silentflowers.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val getUsernameOnAuthentication: GetUsernameOnAuthentication
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
            val token = request.getHeader(HttpHeaders.AUTHORIZATION)

            val username = getUsernameOnAuthentication.getUsername(token)

            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(username, username)
        }

        filterChain.doFilter(request, response)
    }
}