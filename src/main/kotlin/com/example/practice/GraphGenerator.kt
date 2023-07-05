package com.example.practice

class GraphGenerator {
    //Генерация графа
    fun generateGraph(n: Int, m: Int): ArrayList<Node> {
        val graph = ArrayList<Node>()
        var edges = m
        var nodes = n
        if( m > n * (n - 1)){
            println("Invalid data! It will be change to n*(n-1) & n.")
            edges = n * (n - 1)
            nodes = n
        }
        //если количество узлов/мостов отрицательно или равно 0 -> в циклы не входим
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
    public fun intList(list: java.util.ArrayList<Node>): IntArray {
        val arr = IntArray(list.size)
        var i = 0
        for (item in list) {
            arr[i] = item.name
            i++
        }
        return arr
    }
}