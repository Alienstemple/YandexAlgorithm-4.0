/**
 * Яндекс тренировочный контест бэкенд С - Приснится же такое
 */

fun main() {
//    val (N: Int, Q: Int) = readln().split(" ").map { it.toInt() }
//    val swaps: List<Int> = readln().split(" ").map { it.toInt() }

    // TODO n не хранить после иниц потомков

    // Построим дерево
    val tree = Tree(20)
    tree.printTreeBfs()

    val swaps = listOf(2) // FIXME часть теряется без pp 2 3
    swaps.forEach { key ->
        tree.swap(key)
        tree.printTreeBfs()
    }
}

class Node(
    var key: Int,
    var parent: Node? = null,
    val size: Int
) {
    var left: Node? = null
    var right: Node? = null

    init {
        if (key * 2 <= size) {
            left = Node(key * 2, this, size)
        }

        if (key * 2 + 1 <= size) {
            right = Node(key * 2 + 1, this, size)
        }
    }
}

class Tree(n: Int) {
    val treeMap: HashMap<Int, Node> = hashMapOf()  // Нужен, чтобы получить доступ к узлу по ключу
    var root: Node

    private val queue: MutableList<Node> = mutableListOf()

    init {
        root = Node(1, null, n)

        // Инициализируем treeMap
        initTreeMap()
    }
    private fun initTreeMap() {
        queue.add(root)
        bfs { node ->
            treeMap[node.key] = node
        }
    }

    fun printTreeBfs() {
        println("Tree")
        queue.add(root)
        bfs { node ->
            print("${node.key} ")
        }
    }  // BFS

    private fun bfs(action: (node: Node) -> Unit) {
        while (queue.isNotEmpty()) {
            val currentNode = queue.first()
            action(currentNode)
            currentNode.left?.let { left ->
                queue.add(left)
            }
            currentNode.right?.let { right ->
                queue.add(right)
            }
            queue.removeFirst()  // удалим текущий эл-т из головы очереди
        }
    }

    fun swap(key: Int) {
        val v = treeMap[key]!!

        if (key == root.key) // eсли v - корень
            return

        val p: Node = v.parent!!

        if (p.left == v) {
            // case A : v - левый потомок
            val vl: Node? = v.left
            if (vl != null) { // у v есть левый потомок
                vl.parent = p
                p.left = vl
            } else {
                p.left = null // чтобы не было цикла
            }

            workWithPP(v, p)

            v.left = p
            p.parent = v
        } else {
            // case B: v - правый потомок
            val vr: Node? = v.right
            if (vr != null) { // у v есть правый потомок
                vr.parent = p
                p.right = vr
            } else {
                p.right = null // чтобы не было цикла
            }

            workWithPP(v, p)

            v.right = p
            p.parent = v
        }
    }

    private fun workWithPP(v: Node, p: Node) {
        // разберемся с предком предка, если он есть
        val pp: Node? = p.parent
        if (pp != null) {
            if (pp.left == p) { // предок - левый потомок
                pp.left = v
            } else {
                pp.right = v
            }
            v.parent = pp
        }
    }

//        var p = v?.parent
//        var vl = v.left
//        if (p == root) {
//            root = v
//        } else {
//            var pp = p.parent
//            if (p == pp.left) {
//                pp.left = v
//            } else {
//                pp.right = v
//            }
//            v.parent = pp
//        }
//        if (p?.left == v) {  // TDO child null
//            p.left = v.left
//            p.left.parent = p
//            v.left = p
//
//        } else {
//            p.right = v.right
//            v.right = p
//        }
//        p.parent = v
//
//
//        vl.parent = p
//    }

}