package com.lylist.silentflowers.domain.bucket.dto

data class BucketResponse(
    val id: Long,
    val content: String,
    val categories: Set<String>,
    val isSucceed: Boolean,
    val todo: List<TodoResponse>
)