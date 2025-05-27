package com.lylist.silentflowers.persistence.community

import com.lylist.silentflowers.persistence.user.UserJpaEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "tbl_article")
@Entity
class ArticleJpaEntity(
    id: Long? = null,
    user: UserJpaEntity,
    title: String,
    content: String,
    categories: List<String>
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    var id: Long? = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    var user: UserJpaEntity = user
        protected set

    @Column(name = "title", columnDefinition = "VARCHAR(30)", nullable = false, updatable = false, length = 30)
    var title: String = title
        protected set

    @Column(name = "content", columnDefinition = "VARCHAR(300)", nullable = false, updatable = false, length = 300)
    var content: String = content
        protected set

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "categories", columnDefinition = "VARCHAR(100)", nullable = false, updatable = false, length = 100)
    var categories: String = categories.joinToString(separator = ",")
        protected set
}