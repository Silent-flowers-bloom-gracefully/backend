package com.lylist.silentflowers.domain.bucket.port.out.query

import com.lylist.silentflowers.domain.bucket.vo.Todo
import com.lylist.silentflowers.domain.global.interfaces.Query
import com.lylist.silentflowers.domain.global.interfaces.QueryModel

interface FindTodoByIdQuery : Query<FindTodoByIdQuery.Model, Todo?> {

    override fun invoke(model: Model): Todo?

    data class Model(
        val id: Long
    ) : QueryModel
}