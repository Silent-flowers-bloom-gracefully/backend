package com.lylist.silentflowers.domain.user.port.out

import com.lylist.silentflowers.domain.user.vo.User

interface CurrentUserPort {

    operator fun invoke(): User?
}