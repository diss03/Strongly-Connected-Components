package com.example.practice

import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.shape.LineTo
import javafx.scene.text.Font
import kotlin.math.*

class Drawablegraph(var FrontPane: AnchorPane, var graph: OrientedGraph = OrientedGraph()){

    init{
        graph.fillGraph(5, 7)
    }

    fun drawNode(){
        val increment = 360.0/graph.NodeAmount
        for(i in 0 until graph.NodeAmount){

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
//                val line = Line(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY)
//                line.fill = Color.BLACK
//                FrontPane.children.add(line)
                drawArrowLine(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY)
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

    fun drawArrowLine(startX: Double, startY: Double, endX: Double, endY: Double) {
        // get the slope of the line and find its angle
        val slope = (startY - endY) / (startX - endX)
        val lineAngle = atan(slope)
        val arrowAngle = if (startX > endX) Math.toRadians(11.0) else -Math.toRadians(168.0)
        val line = Line(startX, startY, endX, endY)
        val lineLength = sqrt((startX - endX).pow(2.0) + (startY - endY).pow(2.0))
        val arrowLength = 300 / 10

        // create the arrow legs
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