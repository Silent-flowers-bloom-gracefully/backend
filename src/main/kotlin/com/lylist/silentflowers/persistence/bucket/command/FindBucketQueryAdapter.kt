package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.query.FindBucketQuery
import com.lylist.silentflowers.domain.bucket.vo.Bucket
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.persistence.bucket.BucketJpaRepository
import com.lylist.silentflowers.persistence.bucket.BucketVO
import com.lylist.silentflowers.persistence.user.UserVO
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class FindBucketQueryAdapter(
    private val bucketJpaRepository: BucketJpaRepository,
    private val passwordEncoder: PasswordEncoder
) : FindBucketQuery {

    override fun invoke(model: FindBucketQuery.Model): Bucket {
        val bucketEntity = bucketJpaRepository.findById(model.id)
            .orElseThrow {
                StatusException(
                    status = HttpStatus.NOT_FOUND,
                    message = "Bucket not found with id: ${model.id}"
                )
            }
        return BucketVO(bucketEntity, user = UserVO(bucketEntity.user, passwordEncoder))
    }
}