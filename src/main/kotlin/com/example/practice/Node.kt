package com.example.practice

import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.text.Text
import java.lang.reflect.Array

class Node(var name: Int, var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0){

    var visited: Boolean = false
    var timeout: Int = 0
    var adjacents = ArrayList<Node>()
    var revadjacents = ArrayList<Node>()
    var circle = Circle(x, y, r)
    var txt = Text(x, y, "$name")
    var List_of_Lines = ArrayList<Pair<Triple<Line, Line, Line>, Node>>()

    fun createCircle(x: Double, y: Double, r: Double): Circle{
        this.circle = Circle(x, y, r)
        return this.circle
    }

    fun createText(name: Int, x: Double, y: Double): Text{
        this.txt = Text(x, y, "$name")
        return this.txt
    }
}