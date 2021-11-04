package com.yuebaix.pangu.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.ExecutorService
import java.util.function.Supplier

object CoroutineKit {
    private val logger = LoggerFactory.getLogger(CoroutineKit::class.java)

    /*private suspend fun fakeIo(mili: Long) {
        println(Thread.currentThread().name)
        delay(mili)
        print(".")
    }*/

    @JvmStatic
    fun <T> elastic(executor: ExecutorService, vararg suppliers: Supplier<T>): Flux<T> {
        val flux = parallelPublisher(executor, *suppliers)
        flux.blockLast()
        return flux
    }

    @JvmStatic
    fun <T> parallelPublisher(executor: ExecutorService, vararg suppliers: Supplier<T>): Flux<T> {
        var flux = Flux.empty<T>()
        if (suppliers.isNotEmpty()) {
            val list = suppliers.map { supplier -> elasticPublisher(executor.asCoroutineDispatcher(), supplier) }.toList()
            flux = Flux.merge(list)
        }
        return flux
    }

    @JvmStatic
    fun <T> elasticPublisher(executor: ExecutorService, supplier: Supplier<T>): Mono<T> {
        return elasticPublisher(executor.asCoroutineDispatcher(), supplier);
    }

    private fun <T> elasticPublisher(coroutineDispatcher: CoroutineDispatcher, supplier: Supplier<T>): Mono<T> {
        return mono (coroutineDispatcher) {
            wrap(coroutineDispatcher, supplier)
        }
    }

    private suspend fun <T> wrap(coroutineDispatcher: CoroutineDispatcher, supplier: Supplier<T>): T {
        return withContext(coroutineDispatcher) {
            //fakeIo(1000L)
            logger.debug("supplier start")
            val result = supplier.get()
            logger.debug("supplier end")
            return@withContext result
        }
    }
}