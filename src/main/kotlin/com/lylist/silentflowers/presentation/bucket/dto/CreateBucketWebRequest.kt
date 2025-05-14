package com.lylist.silentflowers.presentation.bucket.dto

import com.lylist.silentflowers.domain.bucket.dto.CreateBucketRequest

data class CreateBucketWebRequest(
    override val content: String,
    override val categories: Set<String>
) : CreateBucketRequest