package com.lylist.silentflowers.domain.user.port.`in`

import com.lylist.silentflowers.domain.user.dto.LoginRequest

interface LoginUseCase {

    operator fun invoke(req: LoginRequest): String
}