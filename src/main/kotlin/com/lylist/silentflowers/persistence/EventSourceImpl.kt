package com.lylist.silentflowers.persistence

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.stereotype.Component

@Component
class EventSourceImpl : EventSource {

    private val listeners: Map<Class<*>, Set<EventListener<*>>> = mutableMapOf();

    override fun <T : Event> register(eventListener: EventListener<T>) {
        var listeners: Set<EventListener<*>>? = listeners[eventListener.event()] ?: return

    }

    override fun eventAsync(event: Event) {
        CoroutineScope(Dispatchers.Default).launch {

        }
    }

    override fun eventSync(event: Event) {

    }
}