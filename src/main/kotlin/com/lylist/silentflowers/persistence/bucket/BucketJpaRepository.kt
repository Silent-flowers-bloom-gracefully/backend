package com.lylist.silentflowers.persistence.bucket

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BucketJpaRepository : JpaRepository<BucketJpaEntity, Long> {

    fun findAllByUserId(userId: Long): List<BucketJpaEntity>
}