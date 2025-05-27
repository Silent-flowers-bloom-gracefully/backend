package com.lylist.silentflowers.presentation.bucket

import com.lylist.silentflowers.domain.bucket.port.`in`.CreateTodoUseCase
import com.lylist.silentflowers.domain.bucket.port.`in`.DeleteTodoUseCase
import com.lylist.silentflowers.presentation.bucket.dto.CreateTodoWebRequest
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo")
@RestController
class TodoController(
    private val createTodoUseCase: CreateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createTodo(
        @Valid
        @Positive
        @RequestParam("bucket_id")
        bucketId: Long,
        @Valid
        @RequestBody
        req: CreateTodoWebRequest
    ) {
        createTodoUseCase(req.with(bucketId))
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun deleteTodo(
        @Valid
        @PathVariable
        @Positive
        id: Long
    ) {
        deleteTodoUseCase(id)
    }
}