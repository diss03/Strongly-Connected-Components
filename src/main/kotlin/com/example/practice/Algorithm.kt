package com.example.practice

import java.util.*
val scan = Scanner(System.`in`)

//enum class Mode(val number: Int){
//    AUTO(1), HAND(2)
//}

open class Algorithm {
    open fun dfs(){}
}

class Kosaraju: Algorithm() {
    private var timeout: Int = 1
    var n = 4 //scan.nextInt()
    var m = 4 //scan.nextInt()
    var graph = OrientedGraph()// = OrientedGraph()
    fun dfs(graph: OrientedGraph, vertex: Node) {
        vertex.visited = true
        for (node in vertex.adjacents) {
            if (!node.visited) {
                dfs(graph, node)
            }
        }
        vertex.timeout = timeout
        graph.order.add(vertex)
        timeout += 1
    }

    fun dfs(graph: OrientedGraph, vertex: Node, tmpComp: ArrayList<Int>) {
        vertex.visited = true
        tmpComp.add(vertex.name - 1)

        for (node in vertex.revadjacents) {
            if (!node.visited) {
                dfs(graph, node, tmpComp)
            }
        }
    }

    fun start(mode: Int): String{
        var result = ""
        if(mode == 0) { //AUTO
            graph.fillGraph(n, m)

            for (vertex in 0 until n) {
                if (!graph.graph[vertex].visited) {
                    dfs(graph, graph.graph[vertex])
                }
            }

            if(graph.order.size != 1)
                graph.order = graph.order.reversed() as ArrayList<Node>
            else
                return "1: [] "
            graph.graph.forEach { it.visited = false }
            var i = 0

            for (vertex in graph.order) {
                if (!vertex.visited) {
                    val tmpComp = ArrayList<Int>()
                    dfs(graph, vertex, tmpComp)

                    for (an in 0 .. tmpComp.size-1) {
                        tmpComp[an] =  tmpComp[an]+1
                    }
                    i++

                    result = result + "$i: $tmpComp "
//                    println(result)
                }
            }

        }

        else if(mode == 1) { //HAND
            for (vertex in 0 until n) {
                if (!graph.graph[vertex].visited) {
                    dfs(graph, graph.graph[vertex])
                }
            }

            if(graph.order.size != 1)
                graph.order = graph.order.reversed() as ArrayList<Node>
            else
                return "1: [] "
            graph.graph.forEach { it.visited = false }
            var i = 0

            for (vertex in graph.order) {
                if (!vertex.visited) {
                    val tmpComp = ArrayList<Int>()
                    dfs(graph, vertex, tmpComp)

                    for (an in 0 .. tmpComp.size-1) {
                        tmpComp[an] =  tmpComp[an]+1
                    }
                    i++

                    result = result + "$i: $tmpComp "
//                    println(result)
                }
            }
        }
        else println("Указан неверный режим выполнения")
        return result
    }
}

//Для проверки работы алгоритма запускаем
fun main() {
    val ker = Kosaraju()
    ker.start(0)
}