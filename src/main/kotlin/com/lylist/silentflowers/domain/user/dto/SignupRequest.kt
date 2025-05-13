package com.lylist.silentflowers.domain.user.dto

interface SignupRequest {
    val username: String
    val nickname: String
    val password: String
    val categories: Set<String>
}