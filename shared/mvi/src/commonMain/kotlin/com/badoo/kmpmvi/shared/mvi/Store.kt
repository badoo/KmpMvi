package com.badoo.kmpmvi.shared.mvi

import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable

interface Store<in Intent : Any, out State : Any> : Consumer<Intent>, Observable<State>, Disposable {

    val state: State
}
