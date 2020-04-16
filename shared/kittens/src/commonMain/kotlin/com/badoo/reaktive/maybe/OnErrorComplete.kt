package com.badoo.reaktive.maybe

// Temporary function until we have it in Reaktive (https://github.com/badoo/Reaktive/issues/441)
internal fun <T> Maybe<T>.onErrorComplete(): Maybe<T> = onErrorResumeNext(maybeOfEmpty())
