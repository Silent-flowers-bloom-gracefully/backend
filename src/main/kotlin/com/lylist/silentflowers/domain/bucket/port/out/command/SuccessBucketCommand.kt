package com.lylist.silentflowers.domain.bucket.port.out.command

import com.lylist.silentflowers.domain.global.interfaces.Command
import com.lylist.silentflowers.domain.global.interfaces.CommandModel

interface SuccessBucketCommand : Command<SuccessBucketCommand.Model> {

    override fun invoke(model: Model)

    data class Model(
        val id: Long,
        val success: Boolean
    ) : CommandModel
}