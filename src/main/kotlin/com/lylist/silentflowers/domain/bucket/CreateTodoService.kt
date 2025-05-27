package com.lylist.silentflowers.domain.bucket

import com.lylist.silentflowers.domain.bucket.dto.CreateTodoRequest
import com.lylist.silentflowers.domain.bucket.port.`in`.CreateTodoUseCase
import com.lylist.silentflowers.domain.bucket.port.out.command.CreateTodoCommand
import com.lylist.silentflowers.domain.bucket.port.out.query.FindBucketQuery
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class CreateTodoService(
    private val findBucketQuery: FindBucketQuery,
    private val createTodoCommand: CreateTodoCommand,
    private val currentUserPort: CurrentUserPort
) : CreateTodoUseCase {

    override fun invoke(req: CreateTodoRequest) {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        val bucket = findBucketQuery(
            FindBucketQuery.Model(req.bucketId)
        ) ?: throw StatusException(
            status = HttpStatus.NOT_FOUND,
            message = "Bucket not found with id: ${req.bucketId}"
        )

        if (bucket.user != user) {
            throw StatusException(
                status = HttpStatus.FORBIDDEN,
                message = "You are not the owner of this bucket"
            )
        }

        req.run {
            createTodoCommand(
                CreateTodoCommand.Model(
                    content = content,
                    bucket = bucket
                )
            )
        }
    }
}