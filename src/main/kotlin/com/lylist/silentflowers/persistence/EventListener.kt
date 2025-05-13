package com.lylist.silentflowers.persistence

interface EventListener<T : Event> {

    fun event(): Class<T>

    fun on(event: T)
}