package com.badoo.kmpmvi.shared.mvi

import com.badoo.reaktive.observable.Observable

interface MviView<in Model : Any, out Event : Any> {

    val events: Observable<Event>

    fun render(model: Model)
}
