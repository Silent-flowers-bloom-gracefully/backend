package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.SuccessBucketCommand
import com.lylist.silentflowers.persistence.bucket.BucketJpaEntity
import com.lylist.silentflowers.persistence.bucket.BucketJpaRepository
import org.springframework.stereotype.Component

@Component
class SuccessBucketCommandAdapter(
    private val bucketJpaRepository: BucketJpaRepository
) : SuccessBucketCommand {

    override fun invoke(model: SuccessBucketCommand.Model) {
        bucketJpaRepository.save(
            bucketJpaRepository.findById(model.id).orElseThrow {
                IllegalArgumentException("Bucket with id ${model.id} not found")
            }.run {
                BucketJpaEntity(
                    id = this.id,
                    user = this.user,
                    content = this.content,
                    isSucceed = model.success,
                    categories = this.categories.split(",").toSet()
                )
            }
        )
    }
}