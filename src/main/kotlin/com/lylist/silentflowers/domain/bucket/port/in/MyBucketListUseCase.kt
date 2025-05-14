package com.lylist.silentflowers.domain.bucket.port.`in`

import com.lylist.silentflowers.domain.bucket.dto.BucketListResponse

interface MyBucketListUseCase {

    operator fun invoke(): BucketListResponse
}