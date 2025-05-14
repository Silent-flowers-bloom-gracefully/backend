package com.lylist.silentflowers.domain.bucket.dto

interface CreateBucketRequest {

    val content: String

    val categories: Set<String>
}