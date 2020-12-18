package org.usecase.event

import org.usecase.event.bus.EventBus

object FooEventManager {
    init {
        EventBus.subscribe<FooEvent> { event ->
            dispatchFoo(event)
        }
    }

    private fun dispatchFoo(event: FooEvent) {
        val context = event.context
        // Do something
    }
}
