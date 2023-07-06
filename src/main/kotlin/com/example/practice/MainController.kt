package com.example.practice

import javafx.fxml.FXML
import javafx.scene.Group
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import kotlinx.coroutines.*
import javafx.scene.text.Text
import java.net.URL
import java.util.*
import kotlin.time.times


class MainController {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var AlgBut: Button

    @FXML
    private lateinit var BackPane: AnchorPane

    @FXML
    private lateinit var ClearBut: Button

    @FXML
    private lateinit var DelBut: Button

    @FXML
    lateinit var FrontPane: AnchorPane

    @FXML
    private lateinit var LoadBut: Button

    @FXML
    private lateinit var SaveBut: Button

    @FXML
    private lateinit var VertLine: Line

    @FXML
    private lateinit var WindowForInput: TextField

    @FXML
    private lateinit var StepBut: Button

    private lateinit var draw: Drawablegraph
    private lateinit var obj: Kosaraju
    private var job: Job = Job()
    private var n: Int = 5
    private var m: Int = 7

    @FXML
    fun LoadBut(event: MouseEvent?) {
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()
        val loader = Loader()
        draw.graph.clearGraph()
        draw.graph.graph = loader.loadFromFile("saved_graph.json")
        draw.drawNode()
        draw.drawEdge()
        draw.drawText()
    }

    @FXML
    fun SaveBut(event: MouseEvent?){
        if (job.isActive)
            job.cancel()
        val saver = Saver()
        saver.saveToFile(draw.graph.graph)
    }

    @FXML
    fun ClearClicked(event: MouseEvent?) {
        if (job.isActive)
            job.cancel()
        //obj = Kosaraju(draw.graph)
        draw.graph.clearGraph()
        FrontPane.children.clear()
    }

    @FXML
    fun GenerateBut(event: MouseEvent) {
        if (job.isActive)
            job.cancel()
        if (WindowForInput.text != "") {
            val Node_Edge = WindowForInput.text
            if (Node_Edge.split(' ')[0].toInt() >= 0 && Node_Edge.split(' ')[1].toInt() >= 0) {
                this.n = Node_Edge.split(' ')[0].toInt()
                this.m = Node_Edge.split(' ')[1].toInt()
                WindowForInput.clear()
            } else {
                print("Pls, enter norm values :)")
                WindowForInput.clear()
                return
            }
        }
        this.draw = Drawablegraph(FrontPane, n, m)
        FrontPane.children.clear()
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
    }

    @FXML
    fun StartAlgorithm(event: MouseEvent?) {
        if (job.isActive) {
            job.cancel()
            println("job is done!")
        }
        obj = Kosaraju(draw.graph)
        obj.start()
    }

    @FXML

    fun DeleteClick(event: MouseEvent?) {
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
        for (node in draw.graph.graph) {
            node.circle.setOnMouseClicked {
                (run {
                    FrontPane.children.clear()
                    for (close1 in node.revadjacents) {
                        if (node in close1.adjacents) {
                            close1.adjacents.remove(node)
                        }
                    }
                    for (close2 in node.adjacents) {
                        if (node in close2.revadjacents) {
                            close2.revadjacents.remove(node)
                        }
                    }

                    if (node in draw.graph.order) {
                        draw.graph.order.remove(node)
                    }

                    node.adjacents.clear()
                    node.revadjacents.clear()
                    val ind = draw.graph.graph.indexOf(node)
                    draw.graph.graph.remove(node)

                    for(i in ind until draw.graph.graph.size){
                        draw.graph.graph[i].name -= 1
                    }

                    for (el in draw.graph.graph) {
                        FrontPane.children.add(el.circle)
                    }
                    this.draw.drawEdge()
                    this.draw.drawText()
                })
            }
        }
    }

    @FXML
    fun INputWindowClicked(event: MouseEvent) {
    }

    @FXML
    fun initialize(event: MouseEvent?) {
        this.draw = Drawablegraph(FrontPane, n, m)
    }
    fun stepBut(event: MouseEvent?){
        var step = 1000
        FrontPane.children.clear()
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
        obj = Kosaraju(draw.graph)
        job = GlobalScope.launch(Dispatchers.Main){
            obj.startForStep(draw, FrontPane, step)
        }
    }

}
