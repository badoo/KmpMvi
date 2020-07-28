package com.badoo.kmpmvi.shared.mvi

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.subject.publish.PublishSubject

abstract class AbstractMviView<in Model : Any, Event : Any> : MviView<Model, Event> {

    private val subject = PublishSubject<Event>()
    override val events: Observable<Event> = subject

    open fun dispatch(event: Event) {
        subject.onNext(event)
    }
}
