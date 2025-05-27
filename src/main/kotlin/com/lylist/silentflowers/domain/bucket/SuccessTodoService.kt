package com.lylist.silentflowers.domain.bucket

import com.lylist.silentflowers.domain.bucket.port.`in`.SuccessTodoUseCase
import com.lylist.silentflowers.domain.bucket.port.out.command.SuccessTodoCommand
import com.lylist.silentflowers.domain.bucket.port.out.query.FindTodoByIdQuery
import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.domain.user.port.out.CurrentUserPort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
class SuccessTodoService(
    private val successTodoCommand: SuccessTodoCommand,
    private val findTodoByIdQuery: FindTodoByIdQuery,
    private val currentUserPort: CurrentUserPort
) : SuccessTodoUseCase {

    override fun invoke(id: Long) {
        val user = currentUserPort() ?: throw StatusException(
            status = HttpStatus.UNAUTHORIZED,
            message = "Need to login first"
        )

        val todo = findTodoByIdQuery(
            FindTodoByIdQuery.Model(id)
        ) ?: throw StatusException(
            status = HttpStatus.NOT_FOUND,
            message = "Todo not found with id: $id"
        )

        if (todo.bucket.user != user) {
            throw StatusException(
                status = HttpStatus.FORBIDDEN,
                message = "You are not the owner of this bucket"
            )
        }

        successTodoCommand(
            SuccessTodoCommand.Model(id, todo.isSucceed.not())
        )
    }
}