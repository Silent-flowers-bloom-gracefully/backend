package com.lylist.silentflowers.domain.bucket.vo

import com.lylist.silentflowers.domain.global.interfaces.Projection
import com.lylist.silentflowers.domain.user.vo.User

interface Bucket : Projection {
    val id: Long
    val user: User
    val content: String
    val categories: Set<String>
    val isSucceed: Boolean
}