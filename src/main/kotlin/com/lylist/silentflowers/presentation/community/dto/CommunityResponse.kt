package com.lylist.silentflowers.presentation.community.dto

import com.lylist.silentflowers.domain.user.vo.User
import com.lylist.silentflowers.persistence.community.ArticleJpaEntity

data class CommunityResponse(
    val contents: List<CommunityContentResponse>,
)

data class CommunityContentResponse(
    val id: Long,
    val title: String,
    val content: String,
    val nickname: String,
    val createdAt: String,
    val categories: List<String>,
    val isMy: Boolean
) {
    companion object {
        fun from(entity: ArticleJpaEntity, user: User): CommunityContentResponse {
            return CommunityContentResponse(
                id = entity.id!!,
                title = entity.title,
                content = entity.content,
                categories = entity.categories.split(",").map { it.trim() },
                nickname = entity.user.nickname,
                createdAt = entity.createdAt.toString(),
                isMy = user.getUsername() == entity.user.username
            )
        }
    }
}
