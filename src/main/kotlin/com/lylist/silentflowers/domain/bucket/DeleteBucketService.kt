package com.lylist.silentflowers.domain.bucket

import com.lylist.silentflowers.domain.bucket.port.`in`.DeleteBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.out.command.DeleteBucketCommand
import com.lylist.silentflowers.domain.bucket.port.out.query.FindBucketQuery
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import com.lylist.silentflowers.domain.user.vo.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional


@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class DeleteBucketService(
    private val currentUserPort: CurrentUserPort,
    private val findBucketQuery: FindBucketQuery,
    private val deleteBucketCommand: DeleteBucketCommand,
) : DeleteBucketUseCase {

    override fun invoke(id: Long) {
        val user: User = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        val bucket = findBucketQuery(
            FindBucketQuery.Model(id)
        ) ?: throw StatusException(
            status = HttpStatus.NOT_FOUND,
            message = "Bucket not found with id: $id"
        )

        if (bucket.user != user) {
            throw StatusException(
                status = HttpStatus.FORBIDDEN,
                message = "You are not the owner of this bucket"
            )
        }

        deleteBucketCommand(
            DeleteBucketCommand.Model(id)
        )
    }
}