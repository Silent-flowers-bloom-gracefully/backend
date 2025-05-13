package com.lylist.silentflowers.persistence.bucket

import com.lylist.silentflowers.persistence.user.UserJpaEntity
import jakarta.persistence.*

@Table(name = "tbl_bucket")
@Entity
class BucketJpaEntity(
    id: Long? = null,
    user: UserJpaEntity,
    content: String,
    isSucceed: Boolean,
    categories: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    var user: UserJpaEntity = user
        protected set

    @Column(name = "content", columnDefinition = "VARCHAR(30)", nullable = false, updatable = false, length = 30)
    var content: String = content
        protected set

    @Column(name = "is_succeed", columnDefinition = "BIT", nullable = false, updatable = true)
    var isSucceed: Boolean = isSucceed
        protected set

    @Column(name = "categories", columnDefinition = "VARCHAR(100)", nullable = false, updatable = false, length = 100)
    var categories: String = categories
        protected set
}