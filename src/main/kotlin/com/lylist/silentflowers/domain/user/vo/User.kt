package com.lylist.silentflowers.domain.user.vo

interface User {

    fun getUsername(): String

    fun getNickname(): String

    fun matchPassword(password: String): Boolean
}