package com.badoo.kmpmvi.shared.kittens.integration

import com.badoo.kmpmvi.shared.kittens.KittenView.Event
import com.badoo.kmpmvi.shared.kittens.KittenView.Model
import com.badoo.kmpmvi.shared.kittens.store.KittenStore.Intent
import com.badoo.kmpmvi.shared.kittens.store.KittenStore.State

internal fun State.toModel(): Model =
    Model(
        isLoading = isLoading,
        isError = when (data) {
            is State.Data.Images -> false
            is State.Data.Error -> true
        },
        imageUrls = when (data) {
            is State.Data.Images -> data.urls
            is State.Data.Error -> emptyList()
        }
    )

internal fun Event.toIntent(): Intent =
    when (this) {
        is Event.RefreshTriggered -> Intent.Reload
    }
