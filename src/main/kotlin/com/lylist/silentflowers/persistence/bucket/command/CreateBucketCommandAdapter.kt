package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.CreateBucketCommand
import com.lylist.silentflowers.persistence.bucket.BucketJpaEntity
import com.lylist.silentflowers.persistence.bucket.BucketJpaRepository
import com.lylist.silentflowers.persistence.user.UserVO
import org.springframework.stereotype.Component

@Component
class CreateBucketCommandAdapter(
    private val bucketJpaRepository: BucketJpaRepository
) : CreateBucketCommand {

    override fun invoke(model: CreateBucketCommand.Model) {
        bucketJpaRepository.save(
            BucketJpaEntity(
                content = model.content,
                categories = model.categories,
                user = (model.user as UserVO).getEntity(),
                isSucceed = false
            )
        )
    }
}