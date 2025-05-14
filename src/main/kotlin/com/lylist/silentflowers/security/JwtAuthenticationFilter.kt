package com.lylist.silentflowers.security

import com.lylist.silentflowers.domain.global.StatusException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
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
            try {
                val token = request.getHeader(HttpHeaders.AUTHORIZATION)
                    .replace("Bearer ", "")
                    .replace("Bearer", "")

                val username = getUsernameOnAuthentication.getUsername(token)

                SecurityContextHolder.getContext().authentication =
                    UsernamePasswordAuthenticationToken(username, username, null)

                logger.info("Authenticated user: $username")
            } catch (e: Exception) {
                logger.error("Authentication failed: ${e.message}")
                throw StatusException(
                    status = HttpStatus.UNAUTHORIZED,
                    message = "Invalid token",
                    cause = e
                )
            }
        }

        filterChain.doFilter(request, response)
    }
}