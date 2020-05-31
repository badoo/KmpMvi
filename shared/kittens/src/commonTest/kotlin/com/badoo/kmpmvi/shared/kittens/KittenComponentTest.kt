package com.badoo.kmpmvi.shared.kittens

import com.badoo.kmpmvi.shared.kittens.KittenView.Event
import com.badoo.kmpmvi.shared.kittens.integration.TestKittenDataSource
import com.badoo.kmpmvi.shared.kittens.integration.TestKittenView
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.badoo.reaktive.test.scheduler.TestScheduler
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class KittenComponentTest {

    private val dataSourceScheduler = TestScheduler()
    private val dataSource = TestKittenDataSource(dataSourceScheduler)
    private val view = TestKittenView()

    @BeforeTest
    fun before() {
        overrideSchedulers(main = { TestScheduler() }, computation = { TestScheduler() })
    }

    @AfterTest
    fun after() {
        overrideSchedulers()
    }

    @Test
    fun shows_loading_WHEN_loading_images() {
        dataSourceScheduler.isManualProcessing = true
        dataSource.generateImages()

        startComponent()

        assertTrue(view.model.isLoading)
    }

    @Test
    fun loads_and_shows_images_WHEN_created() {
        val images = dataSource.generateImages()

        startComponent()

        assertEquals(images, view.model.imageUrls)
    }

    @Test
    fun does_not_show_loading_WHEN_images_loaded() {
        dataSource.generateImages()

        startComponent()

        assertFalse(view.model.isError)
    }

    @Test
    fun reloads_images_WHEN_Event_RefreshTriggered() {
        dataSource.generateImages()
        startComponent()
        val newImages = dataSource.generateImages()

        view.dispatch(Event.RefreshTriggered)

        assertEquals(newImages, view.model.imageUrls)
    }

    @Test
    fun shows_error_WHEN_network_error() {
        dataSource.images = null

        startComponent()

        assertEquals(true, view.model.isError)
    }

    @Test
    fun view_not_updated_WHEN_loaded_while_stopped() {
        dataSourceScheduler.isManualProcessing = true
        dataSource.generateImages()
        val component = startComponent()

        component.onStop()
        dataSourceScheduler.isManualProcessing = false

        assertEquals(emptyList(), view.model.imageUrls)
    }

    @Test
    fun shows_images_WHEN_loaded_while_stopped_and_started() {
        dataSourceScheduler.isManualProcessing = true
        val images = dataSource.generateImages()
        val component = startComponent()

        component.onStop()
        dataSourceScheduler.isManualProcessing = false
        component.onStart()

        assertEquals(images, view.model.imageUrls)
    }

    private fun startComponent(): KittenComponent =
        KittenComponent(dataSource).apply {
            onViewCreated(view)
            onStart()
        }
}
