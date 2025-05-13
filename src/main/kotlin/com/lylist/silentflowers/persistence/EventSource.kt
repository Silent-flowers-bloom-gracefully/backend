package com.lylist.silentflowers.persistence

interface EventSource {

    fun <T : Event> register(eventListener: EventListener<T>)

    fun eventAsync(event: Event)

    fun eventSync(event: Event)
}