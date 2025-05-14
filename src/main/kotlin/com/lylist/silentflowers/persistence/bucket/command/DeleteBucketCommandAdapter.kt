package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.DeleteBucketCommand
import com.lylist.silentflowers.persistence.bucket.BucketJpaRepository
import org.springframework.stereotype.Component

@Component
class DeleteBucketCommandImpl(
    private val repository: BucketJpaRepository
) : DeleteBucketCommand {

    override fun invoke(model: DeleteBucketCommand.Model) {
        repository.deleteById(model.id)
    }
}