package com.lylist.silentflowers.persistence.community

import org.springframework.data.jpa.repository.JpaRepository

interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long?> {

    fun findByUserId(userId: Long): List<ArticleJpaEntity>
}