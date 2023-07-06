package com.example.practice

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Line
import kotlinx.coroutines.*
import java.net.URL
import java.util.*


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
    private  var n: Int = 5
    private  var m: Int = 7

    @FXML
    fun LoadBut(event: MouseEvent?) {
        if (job.isActive)
            job.cancel()
        ClearClicked(event)
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
        obj = Kosaraju(draw.graph)
        FrontPane.children.clear()
    }

    @FXML
    fun GenerateBut(event: MouseEvent) {
        if (job.isActive)
        if(WindowForInput.text != ""){
            val Node_Edge = WindowForInput.text
            this.n = Node_Edge.split(' ')[0].toInt()
            this.m = Node_Edge.split(' ')[1].toInt()
            WindowForInput.clear()
        }
        this.draw = Drawablegraph(FrontPane, n, m)
        ClearClicked(event)
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
    fun INputWindowClicked(event: MouseEvent) {
    }

    @FXML
    fun initialize(event: MouseEvent?) {
        this.draw = Drawablegraph(FrontPane, n, m)
    }
    fun stepBut(event: MouseEvent?){
        var step = 1000
        ClearClicked(event)
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
        obj = Kosaraju(draw.graph)
        job = GlobalScope.launch(Dispatchers.Main){
            obj.startForStep(draw, FrontPane, step)
        }
    }

}
