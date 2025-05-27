package com.lylist.silentflowers.persistence.bucket

import com.lylist.silentflowers.domain.bucket.vo.Bucket
import com.lylist.silentflowers.domain.bucket.vo.BucketList
import com.lylist.silentflowers.domain.bucket.vo.Todo
import com.lylist.silentflowers.persistence.user.UserVO

class BucketVO(
    private val entity: BucketJpaEntity,
    override val user: UserVO
) : Bucket {

    override val id: Long
        get() = entity.id!!

    override val content: String
        get() = entity.content

    override val categories: Set<String>
        get() = entity.categories.split(",").toSet()

    override val isSucceed: Boolean
        get() = entity.isSucceed

    override val todoList: List<Todo>
        get() = getEntity().todoList.map {
            TodoVO(it, this)
        }

    fun getEntity(): BucketJpaEntity = entity

    override fun equals(other: Any?): Boolean =
        when (other) {
            is BucketVO -> {
                entity.id == other.entity.id
            }

            is BucketJpaEntity -> {
                entity.id == other.id
            }

            else -> {
                false
            }
        }

    override fun hashCode(): Int {
        var result = entity.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}

data class BucketListVO(
    private val bucketList: List<BucketVO>
) : BucketList {
    override fun invoke(): List<Bucket> = bucketList
}
