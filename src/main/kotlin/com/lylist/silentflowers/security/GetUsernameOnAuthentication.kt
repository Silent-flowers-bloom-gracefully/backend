package com.lylist.silentflowers.security

interface GetUsernameOnAuthentication {

    fun getUsername(authentication: String): String
}