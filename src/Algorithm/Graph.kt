import java.util.ArrayList


class Graph(edges: ArrayList<Pair<Int, Int>>, n: Int) {
    var normalList = Array(n) { mutableListOf<Int>() }
    var inverseList = Array(n) { mutableListOf<Int>() }
    var usedList = Array<Boolean>(n) { false }
    var order = ArrayList<Node>()
    var graph = ArrayList<Node>()

    init {
        for(node in 1..n){
            graph.add(Node(node))
        }
        for ((src, dest) in edges) {
            graph[src - 1].adjacents.add(graph[dest - 1])
            graph[dest - 1].revadjacents.add(graph[src - 1])
        }
    }

    fun printGraph() {
        for (src in normalList.indices) {
            for (dest in normalList[src]) {
                print("($src -> $dest) ")
            }
            print('\n')
        }
    }
}