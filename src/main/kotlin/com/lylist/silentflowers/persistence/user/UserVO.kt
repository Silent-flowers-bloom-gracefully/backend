package com.lylist.silentflowers.persistence.user

import com.lylist.silentflowers.domain.user.vo.User
import org.springframework.security.crypto.password.PasswordEncoder

class UserVO(
    private val entity: UserJpaEntity,
    private val passwordEncoder: PasswordEncoder
) : User {
    override fun getUsername(): String = entity.username

    override fun getNickname(): String = entity.nickname

    override fun matchPassword(password: String): Boolean {
        return passwordEncoder.matches(password, this.entity.password)
    }

    fun getEntity(): UserJpaEntity = entity

    override fun equals(other: Any?): Boolean =
        when (other) {
            is UserVO -> {
                entity.username == other.entity.username
            }

            is UserJpaEntity -> {
                entity.username == other.username
            }

            else -> {
                false
            }
        }
}
