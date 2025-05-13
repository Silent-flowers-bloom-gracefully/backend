package com.lylist.silentflowers.presentation

import com.lylist.silentflowers.domain.user.port.`in`.LoginUseCase
import com.lylist.silentflowers.domain.user.port.`in`.SignupUseCase
import com.lylist.silentflowers.presentation.user.dto.LoginWebRequest
import com.lylist.silentflowers.presentation.user.dto.SignupWebRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val signupUseCase: SignupUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/signup")
    fun signup(
        @Valid
        @RequestBody
        req: SignupWebRequest
    ) {
        signupUseCase(req)
    }

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody
        req: LoginWebRequest,
        response: HttpServletResponse
    ) {
        val token = loginUseCase(req)

        response.setHeader(HttpHeaders.AUTHORIZATION, token)
    }
}