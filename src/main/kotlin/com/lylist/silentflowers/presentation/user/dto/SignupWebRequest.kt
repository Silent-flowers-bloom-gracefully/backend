package com.lylist.silentflowers.presentation.user.dto

import com.lylist.silentflowers.domain.user.dto.SignupRequest

data class SignupWebRequest(
    override val username: String,
    override val nickname: String,
    override val password: String,
    override val categories: Set<String>
) : SignupRequest
