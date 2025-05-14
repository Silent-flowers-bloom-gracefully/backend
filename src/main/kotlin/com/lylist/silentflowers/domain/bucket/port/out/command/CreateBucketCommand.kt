package com.lylist.silentflowers.domain.bucket.port.out.command

import com.lylist.silentflowers.domain.global.interfaces.Command
import com.lylist.silentflowers.domain.global.interfaces.CommandModel
import com.lylist.silentflowers.domain.user.vo.User

interface CreateBucketCommand : Command<CreateBucketCommand.Model> {

    override fun invoke(model: Model)

    data class Model(
        val content: String,
        val categories: Set<String>,
        val user: User
    ) : CommandModel
}