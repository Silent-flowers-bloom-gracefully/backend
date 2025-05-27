package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.DeleteTodoCommand
import com.lylist.silentflowers.persistence.bucket.TodoJpaRepository
import org.springframework.stereotype.Component

@Component
class DeleteTodoCommandAdapter(
    private val todoJpaRepository: TodoJpaRepository
) : DeleteTodoCommand {

    override fun invoke(model: DeleteTodoCommand.Model) {
        model.run {
            todoJpaRepository.deleteById(id)
        }
    }
}