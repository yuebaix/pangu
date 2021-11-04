package com.yuebaix.pangu.coroutine

import java.util.concurrent.Executors
import java.util.function.Supplier
import java.util.stream.Collectors
import java.util.stream.IntStream

fun main() {
    val executor = Executors.newSingleThreadExecutor()

    /*runBlocking {
        repeat(100) {
            launch {
                //CoroutineKit.fakeIo(1000L)
                CoroutineKit.elasticPublisher(coroutineDispatcher, Supplier { "1" }).block()
            }
        }

        *//*launch {
            CoroutineKit.wrap(coroutineDispatcher, Supplier { "1" })
        }*//*
    }*/

    val list: List<Supplier<Int>> = IntStream.range(0, 1000)
        .mapToObj { i ->
            Supplier { i }
        }
        .collect(Collectors.toList())

    val result = CoroutineKit.elastic(executor, *list.toTypedArray())

    result.map({i ->
        println(i)
    }).blockLast()

    executor.shutdown()
    println()
    println("the end")
}