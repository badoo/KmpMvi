package com.badoo.kmpmvi.shared.kittens.integration

import com.badoo.kmpmvi.shared.kittens.KittenView
import com.badoo.kmpmvi.shared.kittens.KittenView.Event
import com.badoo.kmpmvi.shared.kittens.KittenView.Model
import com.badoo.kmpmvi.shared.mvi.AbstractMviView

class TestKittenView : AbstractMviView<Model, Event>(), KittenView {

    lateinit var model: Model

    override fun render(model: Model) {
        this.model = model
    }
}
