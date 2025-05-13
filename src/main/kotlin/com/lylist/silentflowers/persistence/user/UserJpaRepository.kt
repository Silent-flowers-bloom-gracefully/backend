package com.lylist.silentflowers.persistence.user

import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface UserJpaRepository : Repository<UserJpaEntity, Long?> {

    fun save(userJpaEntity: UserJpaEntity): UserJpaEntity

    fun findByUsername(username: String): UserJpaEntity?
}