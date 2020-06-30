package com.hykj.datademo.net

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//fun main() {
//    GlobalScope.launch {
//        println("codes run in coroutine scope")
//        delay(1500)
//        println("codes run in coroutine scope finish")
//    }
//    Thread.sleep(1000)
//}

fun main() {
    runBlocking {
        launch {
            var result = async {
                5 + 54
            }.await()
            println(result)

        }

    }


//    runBlocking {
//        println("codes run in coroutine scope")
//        delay(1500)
//        println("codes run in coroutine scope finish")
//    }
//    Thread.sleep(1000)
//    val start = System.currentTimeMillis()
//    runBlocking {
//        repeat(10000) {
//            launch {
//                println("..")
//            }
//        }
//    }
//    repeat(10000) {
//        launch {
//            println("..")
//        }
//    }
//    val end = System.currentTimeMillis()
//    println(end - start)
}