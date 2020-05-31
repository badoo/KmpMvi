package com.badoo.kmpmvi.shared.kittens.datasource

internal fun makeKittenEndpointUrl(limit: Int, page: Int): String =
    "https://api.thecatapi.com/v1/images/search?limit=$limit&page=$page"
