package com.lylist.silentflowers.domain.bucket.dto

import com.lylist.silentflowers.domain.bucket.vo.BucketList

data class BucketListResponse(
    val bucketList: List<BucketResponse>,
) {
    companion object {
        fun of(bucketList: BucketList): BucketListResponse {
            return BucketListResponse(
                bucketList = bucketList().map {
                    BucketResponse(
                        id = it.id,
                        content = it.content,
                        categories = it.categories,
                        isSucceed = it.isSucceed,
                        todo = it.todoList.map { todo ->
                            TodoResponse(
                                todoId = todo.id,
                                content = todo.content,
                                isSucceed = todo.isSucceed
                            )
                        }
                    )
                }
            )
        }
    }
}

