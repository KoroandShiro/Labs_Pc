package org.example.lab01

fun startAndObserve(target: Thread, pollIntervalMs: Long = 50) {
    val startTime = System.currentTimeMillis()
    var lastState = target.state

    // Imprimir estado inicial antes do start
    println("0 ms ${target.name} $lastState")
    target.start()

    while (true) {
        val currentState = target.state
        val timestamp = System.currentTimeMillis() - startTime

        if (currentState != lastState) {
            println("$timestamp ms ${target.name} $currentState")
            lastState = currentState
        }

        // Sair imediatamente se a thread terminou (evita um sleep final extra)
        if (currentState == Thread.State.TERMINATED) break

        Thread.sleep(pollIntervalMs)
    }
}

fun main(){
    val testThread=Thread {
        println("Start da thread de teste")
    }
    testThread.name = "TestThread"
    startAndObserve(testThread)
    println("Thread de teste terminou")
}