package com.example.practice

class GraphGenerator {
    //Генерация графа
    fun generateGraph(nodes: Int, edges: Int): ArrayList<Node> {
        val graph = ArrayList<Node>()
        for (i in 1..nodes) {
            graph.add(Node(i))
        }
        //Повторяем кол-во вершин раз
        repeat(edges) {
            var node1: Int
            var node2: Int
            do {
                node1 = (0 until nodes).random()
                node2 = (0 until nodes).random()
            } while (node1 == node2 || (intList(graph[node1].adjacents).contains(node2 + 1)))
            graph[node1].adjacents.add(graph[node2])
            graph[node2].revadjacents.add(graph[node1])
        }
        return graph
    }

    //Вспомогательная функция для представления названий вершин в виде IntArray
    private fun intList(list: java.util.ArrayList<Node>): IntArray {
        val arr = IntArray(list.size)
        var i = 0
        for (item in list) {
            arr[i] = item.name
            i++
        }
        return arr
    }

}