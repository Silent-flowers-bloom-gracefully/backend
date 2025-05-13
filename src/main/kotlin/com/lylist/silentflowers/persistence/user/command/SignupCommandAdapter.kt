package com.lylist.silentflowers.persistence.user.command

import com.lylist.silentflowers.domain.user.port.out.command.SignupCommand
import com.lylist.silentflowers.persistence.user.UserJpaEntity
import com.lylist.silentflowers.persistence.user.UserJpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SignupCommandAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder
) : SignupCommand {

    override fun invoke(model: SignupCommand.Model) {
        model.run {
            userJpaRepository.save(
                UserJpaEntity(
                    username = username,
                    nickname = nickname,
                    password = passwordEncoder.encode(password)
                )
            )
        }
    }
}