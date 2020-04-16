package com.badoo.kmpmvi.shared.kittens.integration

import com.badoo.kmpmvi.shared.kittens.store.KittenStoreImpl
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.maybeFromFunction
import com.badoo.reaktive.maybe.onErrorComplete
import com.badoo.reaktive.maybe.subscribeOn
import com.badoo.reaktive.scheduler.computationScheduler
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

internal object KittenStoreParser : KittenStoreImpl.Parser {

    override fun parse(json: String): Maybe<List<String>> =
        maybeFromFunction {
            Json(JsonConfiguration.Stable)
                .parseJson(json)
                .jsonArray
                .map { it.jsonObject.getPrimitive("url").content }
        }
            .subscribeOn(computationScheduler)
            .onErrorComplete()
}
