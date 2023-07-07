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

            val circle = graph.graph[i].createCircle(x, y, 20.0)
            circle.fill = Color.BLACK
            circle.fill
            FrontPane.children.add(circle)
        }
    }
    fun drawNodeWithColor(colors: MutableMap<Int, Color>){
        for(i in 0 until graph.graph.size){
            val circle = graph.graph[i].circle
            circle.fill = colors[i]
            FrontPane.children.add(circle)
        }
    }
    fun drawNodeWithStandart(){
        for(i in 0 until graph.graph.size){

            val circle = graph.graph[i].circle
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
                val line: Line
                val arrow1 = Line()
                val arrow2 = Line()
                val arrowLength = 200 / 10
                val arg1 = findCollision(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY, elem.circle.radius)
                val arg2 = findCollision(elem.circle.centerX, elem.circle.centerY, it.circle.centerX, it.circle.centerY, it.circle.radius)
                val ax = arg1.first
                val ay = arg1.second
                val bx = arg2.first
                val by = arg2.second
                line = Line(bx + it.circle.centerX,by + it.circle.centerY,ax + elem.circle.centerX,ay + elem.circle.centerY)
                arrow1.startX = ax + elem.circle.centerX
                arrow1.startY = ay + elem.circle.centerY
                arrow1.endX = ax + elem.circle.centerX + arrowLength * cos(lineAngle - arrowAngle)
                arrow1.endY = ay + elem.circle.centerY + arrowLength * sin(lineAngle - arrowAngle)
                arrow2.startX = ax + elem.circle.centerX
                arrow2.startY = ay + elem.circle.centerY
                arrow2.endX = ax + elem.circle.centerX + arrowLength * cos(lineAngle + arrowAngle)
                arrow2.endY = ay + elem.circle.centerY + arrowLength * sin(lineAngle + arrowAngle)
                line.strokeWidth = 2.0
                arrow1.strokeWidth = 2.0
                arrow2.strokeWidth = 2.0
                it.List_of_Lines.add(Pair(line, elem))
                FrontPane.children.addAll(line, arrow1, arrow2)
            }
        }
    }
    fun drawReverseEdge(){
        graph.graph.forEach {
            for(elem in it.revadjacents){
                val slope = (it.circle.centerY - elem.circle.centerY) / (it.circle.centerX - elem.circle.centerX)
                val lineAngle = atan(slope)
                val arrowAngle = if (it.circle.centerX >= elem.circle.centerX) Math.toRadians(11.0) else -Math.toRadians(168.0)
                val line: Line
                val arrow1 = Line()
                val arrow2 = Line()
                val arrowLength = 200 / 10
                val arg1 = findCollision(it.circle.centerX, it.circle.centerY, elem.circle.centerX, elem.circle.centerY, elem.circle.radius)
                val arg2 = findCollision(elem.circle.centerX, elem.circle.centerY, it.circle.centerX, it.circle.centerY, it.circle.radius)
                val ax = arg1.first
                val ay = arg1.second
                val bx = arg2.first
                val by = arg2.second
                line = Line(bx + it.circle.centerX,by + it.circle.centerY,ax + elem.circle.centerX,ay + elem.circle.centerY)
                arrow1.startX = ax + elem.circle.centerX
                arrow1.startY = ay + elem.circle.centerY
                arrow1.endX = ax + elem.circle.centerX + arrowLength * cos(lineAngle - arrowAngle)
                arrow1.endY = ay + elem.circle.centerY + arrowLength * sin(lineAngle - arrowAngle)
                arrow2.startX = ax + elem.circle.centerX
                arrow2.startY = ay + elem.circle.centerY
                arrow2.endX = ax + elem.circle.centerX + arrowLength * cos(lineAngle + arrowAngle)
                arrow2.endY = ay + elem.circle.centerY + arrowLength * sin(lineAngle + arrowAngle)
                line.strokeWidth = 2.0
                arrow1.strokeWidth = 2.0
                arrow2.strokeWidth = 2.0
                FrontPane.children.addAll(line, arrow1, arrow2)
            }

        }
    }

    fun drawText(){
        for(i in 0 until graph.graph.size){
            val x = graph.graph[i].circle.centerX
            val y = graph.graph[i].circle.centerY
            val text = graph.graph[i].createText(i, x, y)
            text.font = Font(16.0)
            text.fill = Color.WHITE
            FrontPane.children.add(text)
        }
    }

    /**
     * Функция находящая точку соприкосновнения прямой и окружности
     * @param x1 Точка через которую проходит прямая
     * @param y1 Точка через которую проходит прямая
     * @param x2 Точка через которую проходит прямая и точка центра окружности
     * @param y2 Точка через которую проходит прямая и точка центра окружности
     * @param r Радиус окружности
     * @return Возвращает точку соприкосновения прямой и окружности в виде: Pair<Double, Double>
     */
    private fun findCollision(x1: Double, y1: Double, x2: Double, y2: Double, r: Double): Pair<Double, Double>{
        val k = (y1 - y2) / (x1 - x2)
        val a = -k
        val b = 1
        val c = 0
        val x0 = -a * c / (a * a + b * b)
        val y0 = -b * c / (a * a + b * b)
        if (abs(c * c - r * r * (a * a + b * b)) < 0.01) {
            return Pair(x0,y0)
        }
        else{
            val d = r * r - c * c / (a * a + b * b)
            val mult = sqrt(d / (a * a + b * b))
            val ax: Double
            val ay: Double
            if (x1 < x2){
                ax = x0 - b * mult
                ay = y0 + a * mult
            }
            else {
                ax = x0 + b * mult
                ay = y0 - a * mult
            }
            return Pair(ax, ay)
        }
    }

}