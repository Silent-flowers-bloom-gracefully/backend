package com.lylist.silentflowers.domain.user.port.out.command

import com.lylist.silentflowers.domain.global.interfaces.Command
import com.lylist.silentflowers.domain.global.interfaces.CommandModel

interface SignupCommand : Command<SignupCommand.Model> {

    override fun invoke(model: Model)

    data class Model(
        val username: String,
        val nickname: String,
        val password: String,
        val categories: String
    ) : CommandModel
}