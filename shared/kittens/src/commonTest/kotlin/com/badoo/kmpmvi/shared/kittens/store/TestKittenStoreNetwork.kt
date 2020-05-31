package com.badoo.kmpmvi.shared.kittens.store

import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.map
import com.badoo.reaktive.maybe.observeOn
import com.badoo.reaktive.single.notNull
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.test.scheduler.TestScheduler
import com.badoo.reaktive.utils.atomic.AtomicInt
import com.badoo.reaktive.utils.atomic.AtomicReference
import com.badoo.reaktive.utils.atomic.getValue
import com.badoo.reaktive.utils.atomic.setValue

class TestKittenStoreNetwork(
    private val scheduler: TestScheduler
) : KittenStoreImpl.Network {

    var images by AtomicReference<List<String>?>(null)
    private var seed by AtomicInt()

    override fun load(): Maybe<String> =
        singleFromFunction { images }
            .notNull()
            .map { it.joinToString(separator = SEPARATOR) }
            .observeOn(scheduler)

    fun generateImages(): List<String> {
        val images = List(MAX_IMAGES) { "Img${seed + it}" }
        this.images = images
        seed += MAX_IMAGES

        return images
    }

    private companion object {
        private const val MAX_IMAGES = 50
        private const val SEPARATOR = ";"
    }
}
