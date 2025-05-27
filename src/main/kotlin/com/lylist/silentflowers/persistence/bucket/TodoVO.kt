package com.lylist.silentflowers.persistence.bucket

import com.lylist.silentflowers.domain.bucket.vo.Todo

class TodoVO(
    private val entity: TodoJpaEntity,
    override val bucket: BucketVO
) : Todo {

    override val id: Long
        get() = entity.id!!
    override val content: String
        get() = entity.content
    override val isSucceed: Boolean
        get() = entity.isSucceed

    fun getEntity(): TodoJpaEntity = entity
}