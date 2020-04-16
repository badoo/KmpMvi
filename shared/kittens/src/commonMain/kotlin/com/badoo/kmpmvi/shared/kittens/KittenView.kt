package com.badoo.kmpmvi.shared.kittens

import com.badoo.kmpmvi.shared.kittens.KittenView.Event
import com.badoo.kmpmvi.shared.kittens.KittenView.Model
import com.badoo.kmpmvi.shared.mvi.MviView

interface KittenView : MviView<Model, Event> {

    data class Model(
        val isLoading: Boolean,
        val isError: Boolean,
        val imageUrls: List<String>
    )

    sealed class Event {
        object RefreshTriggered : Event()
    }
}
