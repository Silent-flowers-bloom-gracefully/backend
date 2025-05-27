package com.lylist.silentflowers.domain.bucket.vo

import com.lylist.silentflowers.domain.global.interfaces.Projection

interface Todo : Projection {
    val id: Long
    val content: String
    val isSucceed: Boolean
    val bucket: Bucket
}