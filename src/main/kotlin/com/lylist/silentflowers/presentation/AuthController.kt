package com.lylist.silentflowers.presentation

import com.lylist.silentflowers.domain.user.port.`in`.LoginUseCase
import com.lylist.silentflowers.domain.user.port.`in`.SignupUseCase
import com.lylist.silentflowers.presentation.user.dto.LoginWebRequest
import com.lylist.silentflowers.presentation.user.dto.SignupWebRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val signupUseCase: SignupUseCase,
    private val loginUseCase: LoginUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(
        @Valid
        @RequestBody
        req: SignupWebRequest
    ) {
        signupUseCase(req)
    }

    @ResponseStatus(HttpStatus.OK)
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