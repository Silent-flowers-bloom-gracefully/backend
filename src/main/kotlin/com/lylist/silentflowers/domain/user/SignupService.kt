package com.lylist.silentflowers.domain.user

import com.lylist.silentflowers.domain.user.dto.SignupRequest
import com.lylist.silentflowers.domain.user.port.`in`.SignupUseCase
import com.lylist.silentflowers.domain.user.port.out.command.SignupCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class SignupService(
    private val signupCommand: SignupCommand
) : SignupUseCase {

    override fun invoke(req: SignupRequest) {
        req.run {
            signupCommand(
                SignupCommand.Model(
                    username = username,
                    password = password,
                    nickname = nickname,
                    categories = categories.joinToString(",")
                )
            )
        }
    }
}