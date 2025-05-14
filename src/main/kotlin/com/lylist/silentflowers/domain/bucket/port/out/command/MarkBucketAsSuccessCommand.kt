package com.lylist.silentflowers.domain.bucket.port.out.command

import com.lylist.silentflowers.domain.global.interfaces.Command
import com.lylist.silentflowers.domain.global.interfaces.CommandModel

interface MarkBucketAsSuccessCommand : Command<MarkBucketAsSuccessCommand.Model> {

    override fun invoke(model: Model)

    data class Model(
        val bucketId: Long
    ) : CommandModel
}