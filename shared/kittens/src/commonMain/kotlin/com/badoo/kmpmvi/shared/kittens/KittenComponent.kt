package com.badoo.kmpmvi.shared.kittens

import com.badoo.kmpmvi.shared.kittens.KittenView.Event
import com.badoo.kmpmvi.shared.kittens.datasource.KittenDataSource
import com.badoo.kmpmvi.shared.kittens.integration.KittenStoreNetwork
import com.badoo.kmpmvi.shared.kittens.integration.KittenStoreParser
import com.badoo.kmpmvi.shared.kittens.integration.toIntent
import com.badoo.kmpmvi.shared.kittens.integration.toModel
import com.badoo.kmpmvi.shared.kittens.store.KittenStore.State
import com.badoo.kmpmvi.shared.kittens.store.KittenStoreImpl
import com.badoo.reaktive.annotations.ExperimentalReaktiveApi
import com.badoo.reaktive.disposable.scope.DisposableScope
import com.badoo.reaktive.disposable.scope.disposableScope
import com.badoo.reaktive.observable.map

@OptIn(ExperimentalReaktiveApi::class)
class KittenComponent {

    private val store =
        KittenStoreImpl(
            network = KittenStoreNetwork(dataSource = KittenDataSource()),
            parser = KittenStoreParser
        )

    private var view: KittenView? = null
    private var startStopScope: DisposableScope? = null

    fun onViewCreated(view: KittenView) {
        this.view = view
    }

    fun onStart() {
        val view = requireNotNull(view)

        startStopScope = disposableScope {
            store.map(State::toModel).subscribeScoped(onNext = view::render)
            view.events.map(Event::toIntent).subscribeScoped(onNext = store::onNext)
        }
    }

    fun onStop() {
        startStopScope?.dispose()
    }

    fun onViewDestroyed() {
        view = null
    }

    fun onDestroy() {
        store.dispose()
    }
}
