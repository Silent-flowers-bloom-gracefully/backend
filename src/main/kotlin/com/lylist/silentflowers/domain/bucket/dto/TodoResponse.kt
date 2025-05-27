package com.lylist.silentflowers.domain.bucket.dto

data class TodoResponse(
    val todoId: Long,
    val content: String,
    val isSucceed: Boolean
)