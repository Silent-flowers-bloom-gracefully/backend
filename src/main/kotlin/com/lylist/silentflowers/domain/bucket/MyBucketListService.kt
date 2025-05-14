package com.lylist.silentflowers.domain.bucket

import com.lylist.silentflowers.domain.bucket.dto.BucketListResponse
import com.lylist.silentflowers.domain.bucket.port.`in`.MyBucketListUseCase
import com.lylist.silentflowers.domain.bucket.port.out.query.FindMyBucketListQuery
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class MyBucketListService(
    private val findMyBucketListQuery: FindMyBucketListQuery,
    private val currentUserPort: CurrentUserPort,
) : MyBucketListUseCase {

    override fun invoke(): BucketListResponse = BucketListResponse.of(
        findMyBucketListQuery(
            FindMyBucketListQuery.Model(
                user = currentUserPort() ?: throw StatusException(HttpStatus.UNAUTHORIZED, "Need to login first")
            )
        )
    )
}