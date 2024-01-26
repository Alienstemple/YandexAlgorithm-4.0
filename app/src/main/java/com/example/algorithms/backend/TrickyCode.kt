package com.example.algorithms.backend

/**
 * Яндекс тренировочный контест бэкенд А - Хитрый шифр
 */
fun main() {
//    println("Enter N")
    val N: Int = readln().toInt()
//    println("Enter CSV-s")
    val output: MutableList<String> = mutableListOf()
    for (i in 1..N) {
        val candidate: List<String> = readln().split(',')
//        println(candidate)
        // Различные символы ФИО Бонч-Бруевич  10
        val uniqueSymbols = candidate.take(3).joinToString("").toCharArray().toSet().size
//        println(uniqueSymbols)

        // Сумма цифр в месяце и дне рождения
        val birthDateSum =
            candidate.subList(3, 5).joinToString("").toCharArray().sumOf { it.digitToInt() }
//        println(birthDateSum)

        // Индекс первой буквы фамилии в алфавите
        val nameIndex = candidate[0][0].lowercaseChar().minus('a') + 1
//        println(nameIndex)

        // 16-ричн 3 знака
        var hexValue: String = Integer.toHexString(uniqueSymbols + birthDateSum * 64 + nameIndex * 256).takeLast(3)
        hexValue = hexValue.uppercase()
        when(hexValue.length) {
            1 -> {
                hexValue = "00$hexValue"
            }
            2 -> {
                hexValue = "0$hexValue"
            }
        }
//        println(hexValue)

        output.add("$hexValue ")
    }
    output.forEach{ print("$it ") }
}