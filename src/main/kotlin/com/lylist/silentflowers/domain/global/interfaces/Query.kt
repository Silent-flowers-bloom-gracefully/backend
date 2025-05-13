package com.lylist.silentflowers.domain.global.interfaces

interface Query<Q : QueryModel, P : Projection> {

    operator fun invoke(model: Q): P
}