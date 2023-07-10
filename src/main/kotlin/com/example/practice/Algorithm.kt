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

class Kosaraju(var downlabel: Label = Label(""), var label: Label = Label(""), var graph: OrientedGraph = OrientedGraph()): Algorithm {
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
//          return "1: [1] "
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
    //second откуда, third куда
    //it.List_of_Lines.add(Pair(Triple(line, arrow1, arrow2), elem))
    suspend fun dfsForStep(graph: OrientedGraph, vertex: Node, step: Int) {
        vertex.visited = true

        for (node in vertex.adjacents) {
            if (!node.visited) {
                node.circle.fill = Color.MEDIUMSEAGREEN
                for (line in vertex.List_of_Lines)
                    if (line.second == node) {
                        line.first.first.stroke = Color.MEDIUMSEAGREEN
                        line.first.second.stroke = Color.MEDIUMSEAGREEN
                        line.first.third.stroke = Color.MEDIUMSEAGREEN
                        for (line2 in node.List_of_Lines)
                            if (line2.second == vertex)
                                line2.first.first.stroke = Color.MEDIUMSEAGREEN
                    }
                downlabel.text = " - Vertex ${node.name - 1} is considered"
                delay(step.toDuration(DurationUnit.MILLISECONDS))
                dfsForStep(graph, node, step)
            }
        }
        vertex.timeout = timeout

        graph.order.add(vertex)
        timeout += 1
    }

    suspend fun dfsForStep(graph: OrientedGraph, vertex: Node, tmpComp: ArrayList<Int>, colors: MutableMap<Int, Color>, step: Int, rgb: IntArray) {
        vertex.visited = true
        tmpComp.add(vertex.name - 1)
        vertex.circle.fill = Color.rgb(rgb[0], rgb[1], rgb[2])
        colors.put(vertex.name - 1, Color.rgb(rgb[0], rgb[1], rgb[2]))
        downlabel.text = " - Vertex ${vertex.name - 1} is colored."
        delay(step.toDuration(DurationUnit.MILLISECONDS))
        for (node in vertex.revadjacents) {
            if (!node.visited) {
                for (line in vertex.List_of_Lines)
                    if (line.second == node) {
                        line.first.first.stroke = Color.rgb(rgb[0], rgb[1], rgb[2])
                        line.first.second.stroke = Color.rgb(rgb[0], rgb[1], rgb[2])
                        line.first.third.stroke = Color.rgb(rgb[0], rgb[1], rgb[2])
                        for (line2 in node.List_of_Lines)
                            if (line2.second == vertex)
                                line2.first.first.stroke = Color.rgb(rgb[0], rgb[1], rgb[2])
                    }
                dfsForStep(graph, node, tmpComp, colors, step, rgb)
            }
        }
    }
    suspend fun startForStep(draw: DrawableGraph, window: AnchorPane, step: Int): String{
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
           // return "1: [] "
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

        var r = (0..255).random()
        var g = (0..255).random()
        var b = (0..255).random()
        delay(step.toDuration(DurationUnit.MILLISECONDS))
        val colors = mutableMapOf<Int, Color>()
        for (vertex in graph.order) {
            if (!vertex.visited) {
                val tmpComp = ArrayList<Int>()
                r = (0..255).random()
                g = (0..255).random()
                b = (0..255).random()
                dfsForStep(graph, vertex, tmpComp, colors, step, intArrayOf(r, g, b))

                downlabel.text = " - Component definition $i: "
                i++

            }
        }
        label.text += " - The components of strong connectivity are constructed. \n"
        label.text += " - Amount: $i\n"
        delay(step.toDuration(DurationUnit.MILLISECONDS))
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
                            lines.first.first.strokeWidth = 4.0
                        }
                    }
                }
            }
        }
    }

}

class ForTest(var graph: OrientedGraph = OrientedGraph()){

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

    fun start(): String {
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
        return result
    }
}
