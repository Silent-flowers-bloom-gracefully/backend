package com.lylist.silentflowers.domain.user.port.`in`

import com.lylist.silentflowers.domain.user.dto.SignupRequest

interface SignupUseCase {

    operator fun invoke(req: SignupRequest)
}