package com.lylist.silentflowers.domain.bucket.port.out.command

import com.lylist.silentflowers.domain.bucket.vo.Bucket
import com.lylist.silentflowers.domain.global.interfaces.Command
import com.lylist.silentflowers.domain.global.interfaces.CommandModel

interface CreateTodoCommand : Command<CreateTodoCommand.Model> {

    override fun invoke(model: Model)

    data class Model(
        val bucket: Bucket,
        val content: String
    ) : CommandModel
}