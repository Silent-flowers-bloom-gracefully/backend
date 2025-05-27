package com.lylist.silentflowers.presentation.bucket.dto

import com.lylist.silentflowers.domain.bucket.dto.CreateTodoRequest

data class CreateTodoWebRequest(
    private val content: String
) {

    fun with(bucketId: Long) = CreateTodoRequestImpl(content, bucketId)

    data class CreateTodoRequestImpl(
        override val content: String,
        override val bucketId: Long
    ) : CreateTodoRequest
}
