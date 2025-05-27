package com.lylist.silentflowers.domain.bucket.dto

interface CreateTodoRequest {
    val bucketId: Long
    val content: String
}