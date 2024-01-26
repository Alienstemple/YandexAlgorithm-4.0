package com.example.algorithms.backend

// import SortedMap не дает использовать

/**
 * Яндекс тренировочный контест бэкенд B - Через тернии к клиенту
 */
fun main() {
//    println("Enter N")
    val N: Int = readln().toInt()
//    println("Enter logs")
    val rocketResults: HashMap<String, Long> = hashMapOf() //sortedMapOf(compareBy<String> { it.length }.thenBy { it }) // < id, time >
    for (i in 1..N) {
        val logEntry: List<String> = readln().split(' ')
        val day = logEntry[0]
        val hour = logEntry[1]
        val minute = logEntry[2]
        val id = logEntry[3]
        val status = logEntry[4]
        val timestamp: Long = day.toLong() * 24 * 60 + hour.toLong() * 60 + minute.toLong()
//        println(timestamp)
        val timeEntry: Long = when (status) {
            "A" -> -timestamp
            "B" -> 0
            "C" -> timestamp
            "S" -> timestamp
            else -> 0
        }
        if (rocketResults.containsKey(id)) {
            rocketResults[id] = rocketResults[id]!!.plus(timeEntry)
        } else {
            rocketResults[id] = timeEntry
        }
    }
    val sortedKeys = rocketResults.keys.toMutableList().sortedWith(compareBy<String> { it.length }.thenBy { it })  // TODO length
//    println(sortedKeys)
    sortedKeys.forEach { print("${rocketResults[it]} ") }
}


