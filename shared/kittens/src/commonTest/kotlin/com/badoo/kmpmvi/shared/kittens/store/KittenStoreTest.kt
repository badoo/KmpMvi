package com.badoo.kmpmvi.shared.kittens.store

import com.badoo.kmpmvi.shared.kittens.store.KittenStore.Intent
import com.badoo.kmpmvi.shared.kittens.store.KittenStore.State
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.badoo.reaktive.test.scheduler.TestScheduler
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KittenStoreTest {

    private val parser = TestKittenStoreParser()
    private val networkScheduler = TestScheduler()
    private val network = TestKittenStoreNetwork(networkScheduler)

    @BeforeTest
    fun before() {
        overrideSchedulers(main = { TestScheduler() })
    }

    @AfterTest
    fun after() {
        overrideSchedulers()
    }

    @Test
    fun isLoading_true_WHEN_loading() {
        networkScheduler.isManualProcessing = true
        network.generateImages()

        val store = store()

        assertTrue(store.state.isLoading)
    }

    @Test
    fun loads_images_WHEN_created() {
        val images = network.generateImages()

        val store = store()

        assertEquals(State.Data.Images(urls = images), store.state.data)
    }

    @Test
    fun isLoading_false_WHEN_images_loaded() {
        network.generateImages()

        val store = store()

        assertFalse(store.state.isLoading)
    }

    @Test
    fun error_state_WHEN_network_error() {
        network.images = null

        val store = store()

        assertTrue(store.state.data is State.Data.Error)
    }

    @Test
    fun error_state_WHEN_invalid_response_format() {
        network.images = emptyList()

        val store = store()

        assertTrue(store.state.data is State.Data.Error)
    }

    @Test
    fun reloads_images_WHEN_Intent_Reload() {
        network.generateImages()
        val store = store()
        val newImages = network.generateImages()

        store.onNext(Intent.Reload)

        assertEquals(State.Data.Images(urls = newImages), store.state.data)
    }

    private fun store(): KittenStore = KittenStoreImpl(network, parser)
}
