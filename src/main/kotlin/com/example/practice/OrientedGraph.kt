package com.example.practice


import java.util.ArrayList

class OrientedGraph: Graph {

    var order = ArrayList<Node>()
    var graph = ArrayList<Node>()

    // Генерирует граф с заданным количеством рёбер и вершин(сделать проверку при вводе на невозможное кол-во вершин)
    override fun fillGraph(nodes: Int, edges: Int){
        graph.clear()
        order.clear()
        graph = GraphGenerator().generateGraph(nodes, edges)
    }
    override fun clearGraph() {
        graph.clear()
        order.clear()
    }
}