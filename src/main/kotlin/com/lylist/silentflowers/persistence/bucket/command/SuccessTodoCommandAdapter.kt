package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.SuccessTodoCommand
import com.lylist.silentflowers.persistence.bucket.TodoJpaEntity
import com.lylist.silentflowers.persistence.bucket.TodoJpaRepository
import org.springframework.stereotype.Component

@Component
class SuccessTodoCommandAdapter(
    private val todoJpaRepository: TodoJpaRepository
) : SuccessTodoCommand {

    override fun invoke(model: SuccessTodoCommand.Model) {
        todoJpaRepository.save(
            todoJpaRepository.findById(model.id).orElseThrow {
                IllegalArgumentException("Todo with id ${model.id} not found")
            }.run {
                TodoJpaEntity(
                    id = this.id,
                    bucket = this.bucket,
                    content = this.content,
                    isSucceed = model.success,
                )
            }
        )
    }
}