package com.badoo.kmpmvi.shared.kittens.datasource

import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.maybe
import com.badoo.reaktive.maybe.onErrorComplete
import platform.Foundation.NSData
import platform.Foundation.NSError
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.NSURLResponse
import platform.Foundation.NSURLSession
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataTaskWithURL
import kotlin.native.concurrent.freeze

internal class KittenDataSourceImpl : KittenDataSource {

    override fun load(limit: Int, offset: Int): Maybe<String> =
        maybe<String> { emitter ->
            val callback: (NSData?, NSURLResponse?, NSError?) -> Unit =
                { data: NSData?, _, error: NSError? ->
                    if (data != null) {
                        emitter.onSuccess(NSString.create(data, NSUTF8StringEncoding).toString())
                    } else {
                        emitter.onComplete()
                    }
                }

            val task =
                NSURLSession.sharedSession.dataTaskWithURL(
                    NSURL(string = makeKittenEndpointUrl(limit = limit, offset = offset)),
                    callback.freeze()
                )
            task.resume()
            emitter.setDisposable(Disposable(task::cancel))
        }
            .onErrorComplete()
}
