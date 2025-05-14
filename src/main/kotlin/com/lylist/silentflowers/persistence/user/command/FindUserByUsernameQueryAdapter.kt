package com.lylist.silentflowers.persistence.user.command

import com.lylist.silentflowers.domain.user.port.out.query.FindUserByUsernameQuery
import com.lylist.silentflowers.domain.user.vo.User
import com.lylist.silentflowers.persistence.user.UserJpaRepository
import com.lylist.silentflowers.persistence.user.UserVO
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
                entity = this, passwordEncoder
            )
        }
}