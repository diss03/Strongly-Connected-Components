package com.example.practice

import javafx.scene.shape.Circle
import javafx.scene.text.Text

class Node(val name: Int, var x: Double = 0.0, var y: Double = 0.0, var r: Double = 0.0){

    var visited: Boolean = false
    var timeout: Int = 0
    var adjacents = ArrayList<Node>()
    var revadjacents = ArrayList<Node>()
    var circle = Circle(x, y, r)
    var txt: Text = Text(x, y, "$name")

    fun createCircle(x: Double, y: Double, r: Double): Circle{
        this.x = x
        this.y = y
        this.r = r
        this.circle = Circle(x, y, r)
        return this.circle
    }

    fun createText(): Text{
        this.txt = Text(x, y, "$name")
        return txt
    }



}