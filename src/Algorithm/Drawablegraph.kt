package com.example.practice

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.text.Font
import kotlin.math.cos
import kotlin.math.sin

class Drawablegraph(var FrontPane: AnchorPane, var graph: OrientedGraph = OrientedGraph()){

    init{
        graph.fillGraph(5, 7)
    }

    fun drawNode(){
        val increment = 360.0/graph.NodeAmount
        for(i in 0 until graph.NodeAmount){

            val y = FrontPane.height / 2 + 200 * sin(Math.toRadians((increment*i)))
            val x = FrontPane.width / 2 + 200 * cos(Math.toRadians((increment*i)))

            val circle = graph.graph[i].createCircle(x, y, 20.0)
            circle.fill = Color.BLACK
            FrontPane.children.add(circle)
        }
    }

    fun drawEdge(){
        graph.graph.forEach {
            for(elem in it.adjacents){
                val line = Line(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY)
                line.fill = Color.BLACK
                FrontPane.children.add(line)

            }
        }
    }

    fun drawText(){
        for(i in 0 until graph.NodeAmount){
            val text = graph.graph[i].createText()
            text.font = Font(16.0)
            text.fill = Color.WHITE
            FrontPane.children.add(text)
        }
    }
}