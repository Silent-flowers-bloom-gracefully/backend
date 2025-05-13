package com.lylist.silentflowers.persistence.user.command

import com.lylist.silentflowers.domain.user.port.out.query.FindUserByUsernameQuery
import com.lylist.silentflowers.domain.user.vo.User
import com.lylist.silentflowers.persistence.user.UserJpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class FindUserByUsernameQueryAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val passwordEncoder: PasswordEncoder
) : FindUserByUsernameQuery {

    override fun findUserByUsername(username: String): User? =
        userJpaRepository.findByUsername(username)?.run {
            UserVO(
                username, nickname, password, passwordEncoder
            )
        }

    private class UserVO(
        private val username: String,
        private val nickname: String,
        private val password: String,
        private val passwordEncoder: PasswordEncoder
    ) : User {
        override fun getUsername(): String = username

        override fun getNickname(): String = nickname

        override fun matchPassword(password: String): Boolean {
            return passwordEncoder.matches(password, this.password)
        }
    }
}