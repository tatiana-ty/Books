package ru.geekbrains.books

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlin.reflect.KClass

class RxBus {

    interface Event

    private val bus: Subject<Event> = PublishSubject.create()

    fun publish(event: Event) = bus.onNext(event)

    fun <T : Event> subscribe(clazz: KClass<T>, block: (event: T) -> Unit): Disposable =
        bus.ofType(clazz.java)
            .subscribe({ event -> block(event) }, { /* игнорируем */ })

}