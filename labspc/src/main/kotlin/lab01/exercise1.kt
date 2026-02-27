package org.example.lab01

fun startAndObserve(thread: Thread){
    val P = 5L

    val startTime = System.currentTimeMillis()

    // 1. Guardar o estado inicial e imprimir ANTES de iniciar a thread
    var lastObservedState = thread.state
    println("[${thread.name}] Initial state: $lastObservedState")
    // 2. Iniciar a thread
    thread.start()
    // 3. O ciclo de "Polling" (verificar repetidamente)
    while(lastObservedState != Thread.State.TERMINATED){
        val currentState= thread.state
        // 4. Se o estado mudou desde a última observação, imprimimos o novo estado com o timestamp e o nome da thread
        if(currentState != lastObservedState){
            val timeStamp = System.currentTimeMillis() - startTime
            println("[$timeStamp ms] [${thread.name}] State changed: $currentState")
            lastObservedState = currentState
        }
        // 5. Adormecer a thread atual (a que está a observar) durante P milissegundos
        Thread.sleep(P)

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