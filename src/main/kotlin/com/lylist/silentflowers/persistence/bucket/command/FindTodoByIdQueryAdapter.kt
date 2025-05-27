package com.lylist.silentflowers.persistence.bucket.command

import com.lylist.silentflowers.domain.bucket.port.out.query.FindTodoByIdQuery
import com.lylist.silentflowers.domain.bucket.vo.Todo
import com.lylist.silentflowers.persistence.bucket.BucketVO
import com.lylist.silentflowers.persistence.bucket.TodoJpaRepository
import com.lylist.silentflowers.persistence.bucket.TodoVO
import com.lylist.silentflowers.persistence.user.UserVO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class FindTodoByIdQueryAdapter(
    private val todoJpaRepository: TodoJpaRepository,
    private val passwordEncoder: PasswordEncoder
) : FindTodoByIdQuery {

    override fun invoke(model: FindTodoByIdQuery.Model): Todo? =
        todoJpaRepository.findByIdOrNull(model.id)?.let { todoJpaEntity ->
            return TodoVO(
                todoJpaEntity,
                bucket = BucketVO(
                    todoJpaEntity.bucket, UserVO(
                        todoJpaEntity.bucket.user,
                        passwordEncoder
                    )
                )
            )
        }
}