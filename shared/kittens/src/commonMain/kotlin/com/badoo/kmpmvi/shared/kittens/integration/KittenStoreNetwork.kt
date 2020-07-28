package com.badoo.kmpmvi.shared.kittens.integration

import com.badoo.kmpmvi.shared.kittens.datasource.KittenDataSource
import com.badoo.kmpmvi.shared.kittens.store.KittenStoreImpl
import com.badoo.reaktive.maybe.Maybe

internal class KittenStoreNetwork(
    private val dataSource: KittenDataSource
) : KittenStoreImpl.Network {

    override fun load(): Maybe<String> = dataSource.load(limit = 50, page = 0)
}
