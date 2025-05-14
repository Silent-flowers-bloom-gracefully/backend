package com.lylist.silentflowers.domain.bucket

import com.lylist.silentflowers.domain.bucket.dto.CreateBucketRequest
import com.lylist.silentflowers.domain.bucket.port.`in`.CreateBucketUseCase
import com.lylist.silentflowers.domain.bucket.port.out.command.CreateBucketCommand
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class CreateBucketService(
    private val currentUserPort: CurrentUserPort,
    private val createBucketCommand: CreateBucketCommand
) : CreateBucketUseCase {

    override fun invoke(req: CreateBucketRequest) {
        req.run {
            createBucketCommand(
                CreateBucketCommand.Model(
                    content = content,
                    categories = categories,
                    user = currentUserPort() ?: throw StatusException(HttpStatus.UNAUTHORIZED, "Need to login first")
                )
            )
        }
    }
}