package com.lylist.silentflowers.domain.bucket.port.`in`

import com.lylist.silentflowers.domain.bucket.dto.CreateTodoRequest

interface CreateTodoUseCase {

    operator fun invoke(req: CreateTodoRequest)
}