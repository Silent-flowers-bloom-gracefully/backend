package com.lylist.silentflowers.domain.user.port.out

interface CreateAuthenticationPort {

    fun ofUsername(username: String): String
}