package org.example.lab01

fun main() {
    val iterations = 1000

    // Testa primeiro com a versão pesada, depois comenta esta linha
    // e descomenta a runLight(iterations) para veres a diferença!
    runHeavy(iterations)
    // runLight(iterations)
}

// Variante 1: Instrumentação Pesada (Imprime em TODAS as iterações)
fun runHeavy(iterations: Int) {
    println("--- A iniciar Variante PESADA ---")
    val runHeavyLogic: (Int) -> Unit = { count ->
        for (i in 1..count) {
            println("${Thread.currentThread().name}$i")
            Thread.sleep(1)
        }
    }

    val threadA = Thread({ runHeavyLogic(iterations) }, "A")
    val threadB = Thread({ runHeavyLogic(iterations) }, "B")

    threadA.start()
    threadB.start()
    threadA.join()
    threadB.join()
}

// Variante 2: Instrumentação Leve (Imprime de 100 em 100)
fun runLight(iterations: Int) {
    println("--- A iniciar Variante LEVE ---")
    val runLightLogic: (Int) -> Unit = { count ->
        for (i in 1..count) {
            if (i % 100 == 0) {
                println("${Thread.currentThread().name}$i")
            }
            Thread.sleep(1)
        }
    }

    val threadA = Thread({ runLightLogic(iterations) }, "A")
    val threadB = Thread({ runLightLogic(iterations) }, "B")

    threadA.start()
    threadB.start()
    threadA.join()
    threadB.join()
}