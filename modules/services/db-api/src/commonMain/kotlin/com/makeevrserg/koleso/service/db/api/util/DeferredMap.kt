package com.makeevrserg.koleso.service.db.api.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

fun <T, K> Deferred<T>.map(scope: CoroutineScope = GlobalScope, block: suspend (T) -> K): Deferred<K> {
    return scope.async {
        block.invoke(this@map.await())
    }
}
