package com.badoo.kmpmvi.shared.kittens.datasource

import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.maybeOfEmpty
import com.badoo.reaktive.maybe.subscribeOn
import com.badoo.reaktive.scheduler.ioScheduler

internal class KittenDataSourceImpl : KittenDataSource {

    override fun load(limit: Int, offset: Int): Maybe<String> =
        maybeOfEmpty<String>() // TODO: Implement later
            .subscribeOn(ioScheduler)
}
