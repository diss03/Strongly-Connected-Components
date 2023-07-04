package com.example.practice
import java.io.File
import org.json.JSONObject
class Loader {
    fun loadFromFile(): ArrayList<Node>{
        var graph = ArrayList<Node>()
        val json = File("saved_graph.json").readText()
        val jsonObj = JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1))
        val nodenumb = jsonObj.getInt("NodeNumber")
        for (i in 1..nodenumb){
            graph.add(Node(i))
        }
        for (i in 1..nodenumb){
            val arr = jsonObj.getJSONArray("$i")
            for (item in arr){
                graph[i - 1].adjacents.add(graph[item.toString().toInt() - 1])
                graph[item.toString().toInt() - 1].revadjacents.add(graph[i - 1])
            }
        }
        return graph
    }
}