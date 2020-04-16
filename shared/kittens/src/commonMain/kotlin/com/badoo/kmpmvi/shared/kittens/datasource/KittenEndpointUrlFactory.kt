package com.badoo.kmpmvi.shared.kittens.datasource

internal fun makeKittenEndpointUrl(limit: Int, offset: Int): String =
    "https://api.thecatapi.com/v1/images/search?limit=$limit&offset=$offset"
