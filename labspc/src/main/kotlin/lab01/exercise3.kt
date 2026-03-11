package org.example.lab01

fun main() {
    val run: (Int) -> Unit = { count ->
        for (i in 1..count) {
            println("${Thread.currentThread().name}$i")
            Thread.sleep(1000) // Try changing or removing this sleep
        }
    }
    val threadA = Thread({ run(20) }, "A")
    val threadB = Thread({ run(20) }, "B")

    threadA.start()
    threadB.start()

    threadA.join()
    threadB.join()
}