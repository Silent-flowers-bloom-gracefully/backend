package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.command.CreateTodoCommand
import com.lylist.silentflowers.persistence.bucket.BucketVO
import com.lylist.silentflowers.persistence.bucket.TodoJpaEntity
import com.lylist.silentflowers.persistence.bucket.TodoJpaRepository
import org.springframework.stereotype.Component

@Component
class CreateTodoCommandAdapter(
    private val todoJpaRepository: TodoJpaRepository
) : CreateTodoCommand {

    override fun invoke(model: CreateTodoCommand.Model) {
        model.run {
            todoJpaRepository.save(
                TodoJpaEntity(
                    bucket = (bucket as BucketVO).getEntity(),
                    content = content
                )
            )
        }
    }
}