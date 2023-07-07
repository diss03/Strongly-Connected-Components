package com.example.practice
import org.json.JSONException
import java.io.File
import org.json.JSONObject
class Loader {
    fun loadFromFile(filename: String): ArrayList<Node>{
        var graph = ArrayList<Node>()
        val json = File(filename).readText()
        val jsonObj = JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1))
        val nodenumb = jsonObj.getInt("NodeNumber")
        for (i in 1..nodenumb){
            graph.add(Node(i))
        }
        for (i in 1..nodenumb){
            var arr = jsonObj.getJSONArray("$i")
            for (item in arr){
                graph[i - 1].adjacents.add(graph[item.toString().toInt() - 1])
                graph[item.toString().toInt() - 1].revadjacents.add(graph[i - 1])
            }
            try {
                arr = jsonObj.getJSONArray("${i}.")
                graph[i - 1].createCircle(arr[0].toString().toDouble(), arr[1].toString().toDouble(), 20.0)
            }catch (error: JSONException){

            }

        }
        return graph
    }
}