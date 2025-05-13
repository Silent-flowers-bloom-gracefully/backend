package com.lylist.silentflowers.security

import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import com.lylist.silentflowers.domain.user.port.out.query.FindUserByUsernameQuery
import com.lylist.silentflowers.domain.user.vo.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentUserAdapter(
    private val findUserByUsernameQuery: FindUserByUsernameQuery
) : CurrentUserPort {

    override fun invoke(): User? =
        SecurityContextHolder.getContext().authentication?.let {
            return findUserByUsernameQuery.findUserByUsername(it.principal.toString())
        }
}