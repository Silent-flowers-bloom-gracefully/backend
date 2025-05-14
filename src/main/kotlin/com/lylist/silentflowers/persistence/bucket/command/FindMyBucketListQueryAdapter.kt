package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.query.FindMyBucketListQuery
import com.lylist.silentflowers.domain.bucket.vo.BucketList
import com.lylist.silentflowers.persistence.bucket.BucketJpaRepository
import com.lylist.silentflowers.persistence.bucket.BucketListVO
import com.lylist.silentflowers.persistence.bucket.BucketVO
import com.lylist.silentflowers.persistence.user.UserVO
import org.springframework.stereotype.Component

@Component
class FindMyBucketListQueryAdapter(
    private val bucketJpaRepository: BucketJpaRepository
) : FindMyBucketListQuery {

    override fun invoke(model: FindMyBucketListQuery.Model): BucketList {
        return BucketListVO(bucketJpaRepository.findAllByUserId((model.user as UserVO).getEntity().id!!).map {
            BucketVO(
                entity = it,
                user = model.user
            )
        })
    }
}