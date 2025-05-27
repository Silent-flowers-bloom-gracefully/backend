package com.lylist.silentflowers.persistence.bucket

import jakarta.persistence.*
import org.hibernate.annotations.Cascade

@Table(name = "tbl_todo")
@Entity
class TodoJpaEntity(
    id: Long? = null,
    bucket: BucketJpaEntity,
    content: String,
    isSucceed: Boolean = false,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id", nullable = false, updatable = false)
    var bucket: BucketJpaEntity = bucket
        protected set

    @Column(name = "content", columnDefinition = "VARCHAR(30)", nullable = false, updatable = false, length = 30)
    var content: String = content
        protected set

    @Column(name = "is_succeed", columnDefinition = "BIT", nullable = false, updatable = true)
    var isSucceed: Boolean = isSucceed
        protected set
}