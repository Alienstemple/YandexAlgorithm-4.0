/**
 * Яндекс тренировки по алгоритмам 4.0 Урок 1 задача А - Partition для быстрой сортировки
 * Тестирование:
 * An empty list
 * A single element list
 * A list with all zeros
 * An ordered list
 * A reversed list
 * A list of all the same elements
 * A very large list
 * A list with strange elements (for example, Unicode, negative numbers, overloaded numbers)
 */

fun main() {
    // Ввод
//    println("Enter number of elements")
    val num: Int = readln().toInt()
//    println("Enter array separated with ' '")
    val array: MutableList<Int> = if (num == 0) { // Предусмотрели случай пустого массива
        readln()
        mutableListOf()
    } else {
        readln().split(' ').map { it.toInt() }.take(num).toMutableList()  // TODO прочти про flatMap
    }
//    println("Enter element x")
    val x: Int = readln().toInt()

    // Отладочный вывод
//    println("num = $num")
//    println("array = $array")
//    println("x = $x")

    // Применение partition
    val p = partitionThreePointers(x, array, 0, num)  // partition(x, array, 0, num)
//    println("p = $p")
    val less = array.subList(0, p)
    val greaterOrEq = array.subList(p, num)
//    println("less = $less greaterOrEq = $greaterOrEq")

    // Вывод
//    println("Результат")
    println(less.size)
    println(greaterOrEq.size)
}
fun partition(x: Int, array: MutableList<Int>, l: Int, r: Int): Int {
    var (L, R) = l to r

    // Рассмотрим краевые случаи
    // Массив пустой
    if (array.size == 0) return 0

    while (R - L > 1) {  // TODO оптимизируем проверку выхода за границы массива
        while (R - L > 1 && array[L] < x) {
            L += 1
        }
        while (R > 0 && array[R-1] >= x)  // Берем R-1, т к R указывает за границу подмассива. При этом не допустить выхода за границу массиа влево
            R -= 1
        if (R - L > 1) { // Своп можем сделать, если минимум 2 эл-та, иначе уходим в краевой случай, последний сдвиг указателя L
            swap(array, L, R-1)
            L += 1
            R -= 1
        }
    }
    if (array[L] < x) { // L = R или размер подмассива изначально был 1 (краевой случай сразу сюда)
        L += 1 // здесь выход за границу массива вправо, это можно, т к к элементам больше доступ иметь не будем
    }
    return L
}
fun swap(arr: MutableList<Int>, l: Int, r: Int) {
    val tmp = arr[l]
    arr[l] = arr[r]
    arr[r] = tmp
}

fun partitionThreePointers(x: Int, array: MutableList<Int>, l: Int, r: Int): Int {
    var E = l
    var G = l
    var N = l

    if (array.size == 0) return 0

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
            G++
        } // current > x - ничего не делаем
        N++
    }
    return E
}