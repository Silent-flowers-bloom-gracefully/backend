package com.lylist.silentflowers.presentation.community

import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import com.lylist.silentflowers.persistence.community.ArticleJpaEntity
import com.lylist.silentflowers.persistence.community.ArticleJpaRepository
import com.lylist.silentflowers.persistence.user.UserVO
import com.lylist.silentflowers.presentation.community.dto.CommunityContentResponse
import com.lylist.silentflowers.presentation.community.dto.CommunityResponse
import com.lylist.silentflowers.presentation.community.dto.CreateArticleRequest
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequestMapping("/community")
@RestController
class CommunityController(
    private val articleJpaRepository: ArticleJpaRepository,
    private val currentUserPort: CurrentUserPort
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createArticle(
        @RequestBody
        req: CreateArticleRequest
    ) {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        articleJpaRepository.save(
            ArticleJpaEntity(
                title = req.title,
                content = req.content,
                user = (user as UserVO).getEntity(),
                categories = req.categories
            )
        )
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteArticle(
        @PathVariable("id")
        id: Long
    ) {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        val article = articleJpaRepository.findById(id).orElseThrow {
            StatusException(
                status = HttpStatus.NOT_FOUND,
                message = "Article not found with id: $id"
            )
        }

        if (article.user != user) {
            throw StatusException(
                status = HttpStatus.FORBIDDEN,
                message = "You are not the owner of this article"
            )
        }

        articleJpaRepository.delete(article)
    }

    @GetMapping
    fun getArticles(
    ): CommunityResponse {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        return CommunityResponse(
            contents = articleJpaRepository.findAll().map { CommunityContentResponse.from(it, user) }
        )
    }

    @GetMapping("/my")
    fun getMyArticles(
    ): CommunityResponse {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        return CommunityResponse(
            contents = articleJpaRepository.findByUserId((user as UserVO).getEntity().id!!)
                .map { CommunityContentResponse.from(it, user) }
        )
    }
}