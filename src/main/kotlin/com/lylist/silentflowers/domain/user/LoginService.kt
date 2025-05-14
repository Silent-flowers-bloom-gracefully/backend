package com.lylist.silentflowers.domain.user

import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.dto.LoginRequest
import com.lylist.silentflowers.domain.user.port.`in`.LoginUseCase
import com.lylist.silentflowers.domain.user.port.out.CreateAuthenticationPort
import com.lylist.silentflowers.domain.user.port.out.query.FindUserByUsernameQuery
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class LoginService(
    private val findUserByUsernameQuery: FindUserByUsernameQuery,
    private val createAuthorizationPort: CreateAuthenticationPort
) : LoginUseCase {

    override fun invoke(req: LoginRequest): String {
        val user = findUserByUsernameQuery.findUserByUsername(req.username)
            ?: throw StatusException(HttpStatus.UNAUTHORIZED)

        if (!user.matchPassword(req.password)) {
            throw StatusException(HttpStatus.UNAUTHORIZED)
        }

        return createAuthorizationPort.ofUsername(user.getUsername())
    }
}