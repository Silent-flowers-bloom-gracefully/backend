package com.lylist.silentflowers.domain.bucket.port.`in`

import com.lylist.silentflowers.domain.bucket.dto.CreateBucketRequest

interface CreateBucketUseCase {

    operator fun invoke(req: CreateBucketRequest)
}