package com.example.practice
import org.json.JSONException
import org.json.JSONObject
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset
class Saver {
    fun saveToFile(graph: ArrayList<Node>){
        val path = "saved_graph.json"
        val json = JSONObject()
        try{
            json.put("NodeNumber", graph.size)
            for (node in graph) {
                json.put("${node.name}", GraphGenerator().intList(node.adjacents))
            }
            for (node in graph) {
                json.put("${node.name}.", mutableListOf(node.circle.centerX, node.circle.centerY))
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }
        try {
            PrintWriter(FileWriter(path, Charset.defaultCharset()))
                .use { it.write(json.toString()) }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}