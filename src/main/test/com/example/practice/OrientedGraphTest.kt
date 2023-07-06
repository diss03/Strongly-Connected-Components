package com.example.practice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class OrientedGraphTest {

    //сравниваю количество вершин и ребер (как проверка генератора)
    @Test
    @DisplayName("fillGraph: Fill Graph with one vertex")
    fun fillGraphTest1() {
        var graph = OrientedGraph()
        graph.fillGraph(1,0)
        val nodeArray = graph.graph

        val expected = "1 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }
    @Test
    @DisplayName("fillGraph: Fill disconnected graph (3 vertexes)")
    fun fillGraphTest2() {
        var graph = OrientedGraph()
        graph.fillGraph(3,0)
        val nodeArray = graph.graph

        val expected = "3 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill 2-full graph")
    fun fillGraphTest3() {
        var graph = OrientedGraph()
        graph.fillGraph(2,2)
        val nodeArray = graph.graph

        val expected = "2 2"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill ordinary graph (5 vertexes, 12 ages)")
    fun fillGraphTest4() {
        var graph = OrientedGraph()
        graph.fillGraph(5,12)
        val nodeArray = graph.graph

        val expected = "5 12"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill graph with a negative number of vertexes (-5 vertexes, 0 ages)")
    fun fillGraphTest5() {
        var graph = OrientedGraph()
        graph.fillGraph(-5,0)
        val nodeArray = graph.graph

        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill graph with a 0 number of vertexes (0 vertexes, 0 ages)")
    fun fillGraphTest6() {
        var graph = OrientedGraph()
        graph.fillGraph(-5,0)
        val nodeArray = graph.graph

        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill graph with a negative number of ages (5 vertexes, -10 ages)")
    fun fillGraphTest7() {
        var graph = OrientedGraph()
        graph.fillGraph(5,-10)
        val nodeArray = graph.graph

        val expected = "5 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill graph with a negative number of vertexes and ages (-5 vertexes, -10 ages)")
    fun fillGraphTest8() {
        var graph = OrientedGraph()
        graph.fillGraph(-5,-10)
        val nodeArray = graph.graph

        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("fillGraph: Fill graph with a maximum+1 number of ages (5 vertexes, 21 ages)")
    fun fillGraphTest9() {
        var graph = OrientedGraph()
        graph.fillGraph(5,21)
        val nodeArray = graph.graph

        val expected = "5 20"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }
    @Test
    @DisplayName("clearGraph: Clear empty Graph")
    fun clearGraphTest1() {
        var graph = OrientedGraph()
        graph.fillGraph(0,0)
        graph.clearGraph()

        val nodeArray = graph.graph
        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("clearGraph: Clear 2-full graph")
    fun clearGraphTest2() {
        var graph = OrientedGraph()
        graph.fillGraph(2,2)
        graph.clearGraph()

        val nodeArray = graph.graph
        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("clearGraph: Clear graph with one vertex")
    fun clearGraphTest3() {
        var graph = OrientedGraph()
        graph.fillGraph(1,0)
        graph.clearGraph()

        val nodeArray = graph.graph
        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("clearGraph: Clear disconnected Graph")
    fun clearGraphTest4() {
        var graph = OrientedGraph()
        graph.fillGraph(5,0)
        graph.clearGraph()

        val nodeArray = graph.graph
        val expected = "0 0"
        var actual = ""
        val nodeAmount = nodeArray.size
        var vertexAmount = 0

        for(vertex in nodeArray){
            for(i in vertex.adjacents)
                vertexAmount += 1
        }
        actual = "$nodeAmount $vertexAmount"
        println(actual)
        assertEquals(expected, actual)
    }
}