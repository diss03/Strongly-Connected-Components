package com.example.practice

import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import java.util.*
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import java.time.Duration
import kotlin.coroutines.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlinx.coroutines.javafx.JavaFx as Main



interface Algorithm{
    fun start(): String
}

class Kosaraju(var graph: OrientedGraph = OrientedGraph(), var downlabel: Label = Label(), var label: Label = Label(),): Algorithm {
    private var timeout: Int = 0
    var n = graph.graph.size
    private var job: Job? = null

    fun dfs(graph: OrientedGraph, vertex: Node) {
        timeout += 1
        vertex.visited = true
        for (node in vertex.adjacents) {
            if (!node.visited) {
                dfs(graph, node)
            }
        }
        timeout += 1
        vertex.timeout = timeout
        graph.order.add(vertex)
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
            return "1: [1] "
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
        graph.order.clear()
        label.text = " - The algorithm is completed. The components are built.\n"
        label.text += " - Amount: $i\n"
        find_bridges()
        return result

    }
    suspend fun dfsForStep(graph: OrientedGraph, vertex: Node, step: Int) {
        vertex.visited = true

        for (node in vertex.adjacents) {
            if (!node.visited) {
                node.circle.fill = Color.MEDIUMSEAGREEN
                downlabel.text = " - Vertex ${node.name - 1} is considered"
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
        val result = ""
        delay(step.toDuration(DurationUnit.MILLISECONDS))
        label.text += " - The first DFS is starting...\n"
        for (vertex in 0 until n) {
            if (!graph.graph[vertex].visited) {
                downlabel.text = " - Vertex ${graph.graph[vertex].name - 1} is considered"
                graph.graph[vertex].circle.fill = Color.MEDIUMSEAGREEN
                delay(step.toDuration(DurationUnit.MILLISECONDS))
                dfsForStep(graph, graph.graph[vertex], step)
            }
        }
        label.text += " - The first DFS has completed its work.\n"

        if (graph.order.size > 1)
            graph.order = graph.order.reversed() as ArrayList<Node>
        else if (graph.order.size == 1)
            return "1: [] "
        else
            return ""

        graph.graph.forEach { it.visited = false }

        var i = 0
        delay(step.toDuration(DurationUnit.MILLISECONDS)/2) // для корректной работы логгера
        window.children.clear()
        draw.drawNodeWithStandart()
        draw.drawText()
        draw.drawReverseEdge()
        delay(step.toDuration(DurationUnit.MILLISECONDS)/2)
        label.text += " - The edges have changed direction in the graph.\n"
        delay(step.toDuration(DurationUnit.MILLISECONDS))
        label.text += " - The second DFS is starting...\n"

        delay(step.toDuration(DurationUnit.MILLISECONDS))
        val colors = mutableMapOf<Int, Color>()
        for (vertex in graph.order) {
            if (!vertex.visited) {
                val tmpComp = ArrayList<Int>()
                dfsForStep(graph, vertex, tmpComp)

                val r = (0..255).random()
                val g = (0..255).random()
                val b = (0..255).random()
                downlabel.text = "Component definition $i: "
                delay(step.toDuration(DurationUnit.MILLISECONDS))
                for (an in 0 until tmpComp.size) {
                    tmpComp[an] = tmpComp[an] + 1
                    graph.graph[tmpComp[an] - 1].circle.fill = Color.rgb(r, g, b)
                    downlabel.text = "Vertex ${graph.graph[tmpComp[an] - 1].name - 1} is colored."
                    colors.put(tmpComp[an] - 1, Color.rgb(r, g, b))
                    delay(step.toDuration(DurationUnit.MILLISECONDS))
                }
                i++

            }
        }
        label.text += " - The components of strong connectivity are constructed. \n"
        label.text += " - Amount: $i\n"
        delay(step.toDuration(DurationUnit.MILLISECONDS)/2)
        graph.graph.forEach { it.visited = false }
        window.children.clear()
        draw.drawNodeWithColor(colors)
        draw.drawText()
        draw.drawEdge()
        label.text += " - The edges have original direction in the graph.\n"
        downlabel.text = ""
        find_bridges()
        return result
    }
    fun find_bridges(){
        for(node in graph.graph){
            for(close in node.adjacents){
                if(node.circle.fill != close.circle.fill){
                    for(lines in node.List_of_Lines){
                        if(lines.second == close){
                            lines.first.strokeWidth = 3.5
                        }
                    }
                }
            }
        }
    }
}


//Для проверки работы алгоритма запускаем
fun main() {
}