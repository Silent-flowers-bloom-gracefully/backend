package com.lylist.silentflowers.persistence.bucket

import org.springframework.data.jpa.repository.JpaRepository

interface TodoJpaRepository : JpaRepository<TodoJpaEntity, Long?> {

    fun findAllByBucketId(bucketId: Long): List<TodoJpaEntity>
}