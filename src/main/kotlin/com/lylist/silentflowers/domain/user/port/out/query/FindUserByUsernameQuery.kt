package com.lylist.silentflowers.domain.user.port.out.query

import com.lylist.silentflowers.domain.user.vo.User

interface FindUserByUsernameQuery {

    fun findUserByUsername(username: String): User?
}