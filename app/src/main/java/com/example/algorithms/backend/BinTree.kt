

import kotlinx.coroutines.parkNanosfun
import kotlinx.coroutines.parkNanos main() {
    val n = 16

    val root = Node(1, null, n)  // TODO n не хранить после иниц потомков

}

class Node(
    var key: Int,
    var parent: Node? = null,
    var size: Int
    ) {
        var left: Node? = null,
        var right: Node? = null

        fun initNode() {
            if (key*2 <= size) {
                left = Node(key*2, this, size)
            }

            if (key*2 + 1 <= size) {
                right = Node(key*2 + 1, this, size)
            }
    }
}

class Tree() {
    val treeMap: HashMap<Int, Node> = hashMapOf()  // create

    lateinit var root: Node

    fun printTree() {}  // DFS

    fun swap(key: Int) {
        var v = treeMap[key]
        if (key == root.key) 
            return
        var p = v?.parent
        var vl = v.left
        if (p == root) {
            root = v
        } else {
            var pp = p.parent
            if (p == pp.left) {
                pp.left = v
            } else {
                pp.right = v
            }
            v.parent = pp
        }
        if (p?.left == v) {  // TDO child null
            p.left = v.left
            p.left.parent = p
            v.left = p
            
        } else {
            p.right = v.right
            v.right = p
        }
        p.parent = v

        
        vl.parent = p
    }
}