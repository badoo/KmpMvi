package com.badoo.kmpmvi.shared.kittens.datasource

import com.badoo.reaktive.maybe.Maybe

internal interface KittenDataSource {

    fun load(limit: Int, offset: Int): Maybe<String>
}
