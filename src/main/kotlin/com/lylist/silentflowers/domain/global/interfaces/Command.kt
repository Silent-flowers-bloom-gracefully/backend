package com.lylist.silentflowers.domain.global.interfaces

@FunctionalInterface
interface Command<T : CommandModel> {

    operator fun invoke(model: T)
}