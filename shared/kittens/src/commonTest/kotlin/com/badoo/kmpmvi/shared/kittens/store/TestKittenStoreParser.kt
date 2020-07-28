package com.badoo.kmpmvi.shared.kittens.store

import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.map
import com.badoo.reaktive.maybe.observeOn
import com.badoo.reaktive.single.filter
import com.badoo.reaktive.single.toSingle
import com.badoo.reaktive.test.scheduler.TestScheduler

class TestKittenStoreParser : KittenStoreImpl.Parser {

    override fun parse(json: String): Maybe<List<String>> =
        json
            .toSingle()
            .filter { it != "" }
            .map { it.split(SEPARATOR) }
            .observeOn(TestScheduler())

    private companion object {
        private const val SEPARATOR = ";"
    }
}
