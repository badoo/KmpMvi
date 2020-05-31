package com.badoo.kmpmvi.shared.kittens.integration

import com.badoo.kmpmvi.shared.kittens.datasource.KittenDataSource
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.map
import com.badoo.reaktive.maybe.mapIterable
import com.badoo.reaktive.maybe.observeOn
import com.badoo.reaktive.maybe.onErrorComplete
import com.badoo.reaktive.single.notNull
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.test.scheduler.TestScheduler
import com.badoo.reaktive.utils.atomic.AtomicInt
import com.badoo.reaktive.utils.atomic.AtomicReference
import com.badoo.reaktive.utils.atomic.getValue
import com.badoo.reaktive.utils.atomic.setValue
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal class TestKittenDataSource(
    private val scheduler: TestScheduler
) : KittenDataSource {

    var images by AtomicReference<List<String>?>(null)
    private var seed by AtomicInt()

    override fun load(limit: Int, page: Int): Maybe<String> =
        singleFromFunction { images }
            .notNull()
            .map {
                val offset = page * limit
                it.subList(fromIndex = offset, toIndex = offset + limit)
            }
            .mapIterable { it.toJsonObject() }
            .map { JsonArray(it).toString() }
            .onErrorComplete()
            .observeOn(scheduler)

    private fun String.toJsonObject(): JsonObject =
        JsonObject(mapOf("url" to JsonPrimitive(this)))

    fun generateImages(): List<String> {
        val images = List(MAX_IMAGES) { "Img${seed + it}" }
        this.images = images
        seed += MAX_IMAGES

        return images
    }

    private companion object {
        private const val MAX_IMAGES = 50
    }
}
