package com.badoo.kmpmvi.shared.kittens.store

import com.badoo.kmpmvi.shared.kittens.store.KittenStore.Intent
import com.badoo.kmpmvi.shared.kittens.store.KittenStore.State
import com.badoo.kmpmvi.shared.mvi.Store

internal interface KittenStore : Store<Intent, State> {

    sealed class Intent {
        object Reload : Intent()
    }

    data class State(
        val isLoading: Boolean = false,
        val data: Data = Data.Images()
    ) {

        sealed class Data {
            data class Images(val urls: List<String> = emptyList()) : Data()
            object Error : Data()
        }
    }

}
