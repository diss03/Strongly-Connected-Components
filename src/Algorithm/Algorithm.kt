import java.util.*
val scan = Scanner(System.`in`)

open class Algorithm {
    open fun dfs(){}
}

class Kesarajo: Algorithm() {
    private var timeout: Int = 1
    private val edges = ArrayList<Pair<Int, Int>>()
    private val n = scan.nextInt()
    private val m = scan.nextInt()

    fun input(){
        var u: Int
        var v: Int

        for (i in 0 until m) {
            u = scan.nextInt()
            v = scan.nextInt()
            edges.add(Pair(u, v))
        }
    }

    fun dfs(graph: Graph, ver: Node) {

        ver.visited = true
        for (node in ver.adjacents) {
            if (!node.visited) {
                dfs(graph, node)
            }
        }
        ver.timeout = timeout
        graph.order.add(ver)
        timeout += 1
    }

    fun dfs(graph: Graph, ver: Node, tmpComp: ArrayList<Int>) {
        ver.visited = true
        tmpComp.add(ver.name - 1)

        for (node in ver.revadjacents) {
            if (!node.visited) {
                dfs(graph, node, tmpComp)
            }
        }
    }

    fun start(){
            val graph = Graph(edges, n)

            for (ver in 0 until n) {
                if(!graph.graph[ver].visited){
                    dfs(graph, graph.graph[ver])
                }
            }

            graph.order = graph.order.reversed() as ArrayList<Node>
            graph.graph.forEach { it.visited = false }
            var i = 0

            for (ver in graph.order) {
                if (!ver.visited) {
                    val tmpComp = ArrayList<Int>()
                    dfs(graph, ver, tmpComp)

                    print("$i component: [")
                    i++
                    for (elem in tmpComp) {
                        print(" ${elem + 1} ")
                    }
                    print("]\n")
                }
            }
        }

}


fun main() {
    val ker = Kesarajo()
    ker.input()
    ker.start()

}