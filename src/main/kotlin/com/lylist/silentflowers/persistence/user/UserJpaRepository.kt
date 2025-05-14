package com.lylist.silentflowers.persistence.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserJpaEntity, Long?> {

    fun findByUsername(username: String): UserJpaEntity?
}