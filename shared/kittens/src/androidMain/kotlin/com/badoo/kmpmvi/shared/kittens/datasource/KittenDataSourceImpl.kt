package com.badoo.kmpmvi.shared.kittens.datasource

import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.maybeFromFunction
import com.badoo.reaktive.maybe.onErrorComplete
import com.badoo.reaktive.maybe.subscribeOn
import com.badoo.reaktive.scheduler.ioScheduler
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

internal class KittenDataSourceImpl : KittenDataSource {

    override fun load(limit: Int, offset: Int): Maybe<String> =
        maybeFromFunction {
            val url = URL(makeKittenEndpointUrl(limit = limit, offset = offset))
            val connection = url.openConnection() as HttpURLConnection

            connection
                .inputStream
                .bufferedReader()
                .use(BufferedReader::readText)
        }
            .subscribeOn(ioScheduler)
            .onErrorComplete()
}
