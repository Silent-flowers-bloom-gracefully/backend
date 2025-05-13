package com.lylist.silentflowers.presentation.user.dto

import com.lylist.silentflowers.domain.user.dto.LoginRequest

data class LoginWebRequest(
    override val username: String,
    override val password: String
) : LoginRequest