package com.badoo.kmpmvi.shared.mvi

import com.badoo.reaktive.observable.Observable

typealias Reducer<State, Effect> = (state: State, effect: Effect) -> State

typealias Actor<State, Intent, Effect> = (state: State, intent: Intent) -> Observable<Effect>
