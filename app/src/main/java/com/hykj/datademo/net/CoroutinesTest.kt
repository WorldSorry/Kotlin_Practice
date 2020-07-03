package com.hykj.datademo.net

import kotlinx.coroutines.*

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
//            var result = async {
//                5 + 54
//            }.await()
            var result= withContext(Dispatchers.Default){
                5+60
            }
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