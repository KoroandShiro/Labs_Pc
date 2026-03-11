package org.example.lab01

fun main(){
    println("Main thread started")
    val sleeper = Thread({
        println("start")
        Thread.sleep(2000)
        println("end")
    }, "sleeper")

    startAndObserve(sleeper, 10)
    println("end")
}