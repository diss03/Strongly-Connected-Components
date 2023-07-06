package com.example.practice

import javafx.event.EventHandler
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import java.util.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.time.Duration
import kotlin.coroutines.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlinx.coroutines.javafx.JavaFx as Main
val scan = Scanner(System.`in`)

//enum class Mode(val number: Int){
//    AUTO(1), HAND(2)
//}

interface Algorithm{
    fun start(): String
}

class Kosaraju(var graph: OrientedGraph = OrientedGraph()): Algorithm {
    private var timeout: Int = 1
    var n = graph.graph.size
    private var job: Job? = null

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

    override fun start(): String {
        var result = ""
        for (vertex in 0 until n) {
            if (!graph.graph[vertex].visited) {
                dfs(graph, graph.graph[vertex])
            }
        }

        if (graph.order.size > 1)
            graph.order = graph.order.reversed() as ArrayList<Node>
        else if (graph.order.size == 1)
            return "1: [] "
        else
            return ""

        graph.graph.forEach { it.visited = false }
        var i = 0

        for (vertex in graph.order) {
            if (!vertex.visited) {
                val tmpComp = ArrayList<Int>()
                dfs(graph, vertex, tmpComp)

                val r = (0..255).random()
                val g = (0..255).random()
                val b = (0..255).random()

                for (an in 0 until tmpComp.size) {
                    tmpComp[an] = tmpComp[an] + 1
                    graph.graph[tmpComp[an] - 1].circle.fill = Color.rgb(r, g, b)
                }
                i++

                result = "$result$i: $tmpComp "

            }
        }
        println(result)
        graph.graph.forEach { it.visited = false }
        return result
    }
    suspend fun dfsForStep(graph: OrientedGraph, vertex: Node, step: Int) {
        vertex.visited = true
        for (node in vertex.adjacents) {
            if (!node.visited) {
                node.circle.fill = Color.RED
                delay(step.toDuration(DurationUnit.MILLISECONDS))
                dfsForStep(graph, node, step)
            }
        }
        vertex.timeout = timeout
        graph.order.add(vertex)
        timeout += 1
    }

    suspend fun dfsForStep(graph: OrientedGraph, vertex: Node, tmpComp: ArrayList<Int>) {
        vertex.visited = true
        tmpComp.add(vertex.name - 1)

        for (node in vertex.revadjacents) {
            if (!node.visited) {
                dfsForStep(graph, node, tmpComp)
            }
        }
    }
    suspend fun startForStep(draw: Drawablegraph, window: AnchorPane, step: Int): String{
        var result = ""
        for (vertex in 0 until n) {
            if (!graph.graph[vertex].visited) {
                graph.graph[vertex].circle.fill = Color.RED
                delay(step.toDuration(DurationUnit.MILLISECONDS))
                dfsForStep(graph, graph.graph[vertex], step)
            }
        }

        if (graph.order.size > 1)
            graph.order = graph.order.reversed() as ArrayList<Node>
        else if (graph.order.size == 1)
            return "1: [] "
        else
            return ""

        graph.graph.forEach { it.visited = false }
        var i = 0
        /*
        for (node in graph.graph){
            val tmp = node.adjacents
            node.adjacents = node.revadjacents
            node.revadjacents = tmp
        }
        delay(1000)
        for (node in graph.graph){
            val tmp = node.adjacents
            node.adjacents = node.revadjacents
            node.revadjacents = tmp
        }

         */
        window.children.clear()
        draw.drawNode()
        draw.drawText()
        draw.drawReverseEdge()
        delay(step.toDuration(DurationUnit.MILLISECONDS)/2)
        var colors = mutableMapOf<Int, Color>()
        for (vertex in graph.order) {
            if (!vertex.visited) {
                val tmpComp = ArrayList<Int>()
                dfsForStep(graph, vertex, tmpComp)

                val r = (0..255).random()
                val g = (0..255).random()
                val b = (0..255).random()

                for (an in 0 until tmpComp.size) {
                    tmpComp[an] = tmpComp[an] + 1
                    graph.graph[tmpComp[an] - 1].circle.fill = Color.rgb(r, g, b)
                    colors.put(tmpComp[an] - 1, Color.rgb(r, g, b))
                    delay(step.toDuration(DurationUnit.MILLISECONDS))
                }
                i++


            }
        }
        graph.graph.forEach { it.visited = false }
        window.children.clear()
        draw.drawNodeWithColor(colors)
        draw.drawText()
        draw.drawEdge()
        return result
    }
}


//Для проверки работы алгоритма запускаем
fun main() {
    val ker = Kosaraju()
    ker.start()
}