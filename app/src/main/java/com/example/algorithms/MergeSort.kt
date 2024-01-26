package com.example.algorithms

/**
 * Яндекс тренировки по алгоритмам 4.0 Урок 1 задача C - Merge для сортировки слиянием
 * Память, кольцевой буфер
 */
fun main() {
    val array1 = mutableListOf<Int>() // mutableListOf(1, 3, 5, 6, 7, 7, 8)
    val array2 = mutableListOf<Int>() // mutableListOf(1, 2, 2, 4, 6, 9, 10, 11, 12)
    val resultArray = merge(array1, array2)
    println(resultArray)
}

fun merge(array1: MutableList<Int>, array2: MutableList<Int>): MutableList<Int> {
    var p1 = 0
    var p2 = 0
    val result = mutableListOf<Int>()
    while (p1 < array1.size && p2 < array2.size) {
        if (array1[p1] < array2[p2]) {
            result.add(array1[p1])
            p1++
        } else {
            result.add(array2[p2])
            p2++
        }
    }
    if (p1 >= array1.size)
        result.addAll(array2.subList(p1, array2.size))
    if (p2 >= array2.size)
        result.addAll(array1.subList(p2, array1.size))

    return result
}