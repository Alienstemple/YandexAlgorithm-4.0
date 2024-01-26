package com.example.algorithms

import partition
import partitionThreePointers

/**
 * Яндекс тренировки по алгоритмам 4.0 Урок 1 задача B - Быстрая сортировка
 */

fun main() {
    QuickSort().run()
}

class QuickSort {
    private lateinit var array: MutableList<Int>
    fun run() {
        // Ввод
//    println("Enter number of elements")
        val num: Int = readln().toInt()
//    println("Enter array separated with ' '")
        array = if (num == 0) { // Предусмотрели случай пустого массива
            readln()
            mutableListOf()
        } else {
            readln().split(' ').map { it.toInt() }.take(num).toMutableList()
        }

        quicksort(0, array.size)

        array.forEach{ print("$it ") }
    }

    private fun quicksort(l: Int, r: Int) {  // TODO когда есть эл-т = 0 заменяется на 1 (threePointers) И проверим тест 1 0 3 0
        if (r - l <= 1) return // 1 эл-т, возвращаемся
//        if (r - l < 1) return

        val x = array[(l until r).random()]  // ML sublist
        val p = partitionThreePointers(x, l, r) //partition(x, l, r)
        println("$array l = $l r = $r x = $x")

        if (r - l > 2) {  // 3 эл-та и больше partition и quicksort
            quicksort(l, p)
            quicksort(p+1, r)
        }
    }

    private fun partitionThreePointers(x: Int, l: Int, r: Int): Int {
        var E = l
        var G = l
        var N = l

        while (N < r) {
            val current = array[N]
            if (current < x) {
                array[N] = array[G]
                array[G] = array[E]
                array[E] = current
                G++
                E++
            } else if (current == x) {
                array[N] = array[G]
                array[G] = current
                G++
            } // current > x - ничего не делаем
            N++
        }
        return E
    }

    private fun partition(x: Int, l: Int, r: Int): Int {
        var (L, R) = l to r

        // Рассмотрим краевые случаи
        // Массив пустой
        if (array.size == 0) return 0

        while (L <= R) {  // TODO оптимизируем проверку выхода за границы массива
            while (L <= R && array[L] < x) {
                L += 1
            }
            while (L <= R && array[R] >= x)  // Берем R-1, т к R указывает за границу подмассива. При этом не допустить выхода за границу массиа влево
                R -= 1
            if (L <= R) { // Своп можем сделать, если минимум 2 эл-та, иначе уходим в краевой случай, последний сдвиг указателя L
                swap(L, R)
            }
        }
        return L
    }
    private fun swap(l: Int, r: Int) {
        val tmp = array[l]
        array[l] = array[r]
        array[r] = tmp
    }
}
