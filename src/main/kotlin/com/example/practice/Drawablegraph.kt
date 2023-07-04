package com.example.practice

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.text.Font
import kotlin.math.*

class Drawablegraph(var FrontPane: AnchorPane, n: Int = 5, m: Int = 7, var graph: OrientedGraph = OrientedGraph()){

    init{
        graph.fillGraph(n, m)
    }

    fun drawNode(){
        val increment = 360.0/graph.graph.size
        for(i in 0 until graph.graph.size){

            val y = FrontPane.height / 2 + 200 * sin(Math.toRadians((increment*i)))
            val x = FrontPane.width / 2 + 200 * cos(Math.toRadians((increment*i)))

            val circle = graph.graph[i].createCircle(x, y, 15.0)
            circle.fill = Color.BLACK
            FrontPane.children.add(circle)
        }
    }

    fun drawEdge(){
        graph.graph.forEach {
            for(elem in it.adjacents){
                val slope = (it.circle.centerY - elem.circle.centerY) / (it.circle.centerX - elem.circle.centerX)
                val lineAngle = atan(slope)
                val arrowAngle = if (it.circle.centerX >= elem.circle.centerX) Math.toRadians(11.0) else -Math.toRadians(168.0)
                val line = Line(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY)
                //val lineLength = sqrt((it.circle.centerX - elem.circle.centerX).pow(2.0) + (it.circle.centerY - elem.circle.centerY).pow(2.0))
                val arrowLength = 300 / 10

                val arrow1 = Line()
                arrow1.startX = line.endX
                arrow1.startY = line.endY
                arrow1.endX = line.endX + arrowLength * cos(lineAngle - arrowAngle)
                arrow1.endY = line.endY + arrowLength * sin(lineAngle - arrowAngle)
                val arrow2 = Line()
                arrow2.startX = line.endX
                arrow2.startY = line.endY
                arrow2.endX = line.endX + arrowLength * cos(lineAngle + arrowAngle)
                arrow2.endY = line.endY + arrowLength * sin(lineAngle + arrowAngle)
                FrontPane.children.addAll(line, arrow1, arrow2)
            }
        }
    }

    fun drawText(){
        for(i in 0 until graph.graph.size){
            val text = graph.graph[i].createText()
            text.font = Font(16.0)
            text.fill = Color.WHITE
            FrontPane.children.add(text)
        }
    }
    fun create_graph(n: Int = 5, m: Int = 7){
        graph.fillGraph(n, m)
    }
}