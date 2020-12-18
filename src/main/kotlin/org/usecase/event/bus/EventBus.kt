package org.usecase.event.bus

import org.usecase.event.*
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.apache.logging.log4j.LogManager
import org.usecase.event.Event

object EventBus {
    @PublishedApi internal val logger = LogManager.getLogger(EventBus::class.java)
    val publisher: PublishSubject<Event> = PublishSubject.create()

    inline fun <reified T: Event> subscribe(noinline consumer: (T) -> Unit): Disposable {
        return publisher.filter {
            it is T
        }.map {
            it as T
        }.subscribe {
            runCatching { consumer.invoke(it) }
                .onFailure { logger.warn(it.localizedMessage, it) }
        }
    }

    fun emit(event: Event) {
        publisher.onNext(event)
    }

    init {
        FooEventManager
    }
}
