package com.lylist.silentflowers.domain.bucket.vo

import com.lylist.silentflowers.domain.global.interfaces.Projection

interface BucketList : Projection {
    operator fun invoke(): List<Bucket>
}