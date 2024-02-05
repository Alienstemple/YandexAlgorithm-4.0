
val array = listOf(1,3,4,6,9,10,11,13)

fun main() {
    

    val index = binsearch(0, array.size, 14)
    println(index)
}

fun binsearch(start: Int, end: Int, target: Int): Int {
    var s = start
    var e = end
    var mid: Int = (e+s)/2
    while(s < e && target != array[mid]) {
        println("s = $s e = $e")

        if (array[mid] > target){
            e = mid-1  // TODO не включая е
        } else {
            s = mid +1
        }
        mid = (e+s)/2
    }

    if (mid >= array.size || array[mid] != target) {
        return -1
    }

    return mid
}


fun timeline () {
    val timeline = mutableListOf(0,0,0,0,0,0,0,0,0)

    val query1 = Query(3, 6, 1)
    val query2 = Query(2, 7, 2)
    
    timeline[query1.start] = query1.add
    timeline[query1.end] = -query1.add

    timeline[query2.start] = query2.add
    timeline[query2.end] = -query2.add

    for (i in 1 .. timeline.size-1) { 
        timeline[i] += timeline[i-1]
    }

    println(timeline)
}

data class Query(
    val start: Int,
    val end: Int,
    val add: Int
)