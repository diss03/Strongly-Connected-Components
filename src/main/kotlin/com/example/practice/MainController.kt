package com.example.practice

import com.jfoenix.controls.JFXSlider
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Line
import javafx.scene.text.Font
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.cancellation.CancellationException
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
    private lateinit var FrontPane: AnchorPane

    @FXML
    private lateinit var LoadBut: Button

    @FXML
    private lateinit var SaveBut: Button


    @FXML
    private lateinit var slider: JFXSlider

    @FXML
    private lateinit var VertLine: Line

    @FXML
    private lateinit var WindowForInput: TextField

    @FXML
    private lateinit var label: Label

    @FXML
    private lateinit var StepBut: Button

    private lateinit var draw: Drawablegraph
    private lateinit var obj: Kosaraju
    private val list_lines = ArrayList<Triple<Line, Node, Node>>()
    private var job: Job = Job()
    private var n: Int = 5
    private var m: Int = 7

    @FXML
    fun LoadBut(event: MouseEvent?) {
        label.text = ""
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()
        val loader = Loader()
        draw.graph.clearGraph()
        draw.graph.graph = loader.loadFromFile("saved_graph.json")
        obj = Kosaraju(label, draw.graph)
        draw.drawNode()
        draw.drawEdge()
        draw.drawText()
    }

    @FXML
    fun SaveBut(event: MouseEvent?){
        if (job.isActive) {
            job.cancel()
            FrontPane.children.clear()
            draw.drawNode()
            draw.drawEdge()
            draw.drawText()
        }
        val saver = Saver()
        saver.saveToFile(draw.graph.graph)
    }

    @FXML
    fun ClearClicked(event: MouseEvent?) {
        label.text = ""
        if (job.isActive)
            job.cancel()
        //obj = Kosaraju(draw.graph)
        draw.graph.clearGraph()
        FrontPane.children.clear()
    }

    @FXML
    fun GenerateBut(event: MouseEvent) {
        label.text = ""
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
        obj = Kosaraju(label, draw.graph)
        FrontPane.children.clear()
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
    }

    @FXML
    fun StartAlgorithm(event: MouseEvent?) {
        if (job.isActive) {
            job.cancel()
            obj.graph.graph.forEach { it.visited = false }
            obj.graph.order.clear()
            println("job is done!")
        }
        obj = Kosaraju(label, draw.graph)
        obj.start()
    }

    @FXML
    fun DeleteClick(event: MouseEvent?) {
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()
        this.draw.drawNodeWithStandart()
        this.draw.drawEdge()
        this.draw.drawText()
        for(node in draw.graph.graph){
            for(line in node.List_of_Lines){
                list_lines.add(Triple(line.first, node, line.second))
            }
        }
        for(line in list_lines){
            line.first.setOnMouseClicked {
                (run {
                FrontPane.children.clear()
                println("ubh")
                line.second.adjacents.remove(line.third)
                line.third.revadjacents.remove(line.second)
                line.second.List_of_Lines.remove(Pair(line.first, line.third))

                for (el in draw.graph.graph) {
                    FrontPane.children.add(el.circle)
                }

                this.draw.drawEdge()
                this.draw.drawText()
                    for (node in draw.graph.graph) {
                        node.circle.setOnMouseClicked {}
                    }
            }) }
        }
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

                    for (i in ind until draw.graph.graph.size) {
                        draw.graph.graph[i].name -= 1
                    }

                    for (el in draw.graph.graph) {
                        FrontPane.children.add(el.circle)
                    }
                    this.draw.drawEdge()
                    this.draw.drawText()
                    for (node2 in draw.graph.graph) {
                        node2.circle.setOnMouseClicked {}
                    }
                })
            }
        }

    }

    @FXML
    fun INputWindowClicked(event: MouseEvent) {
    }
    fun stepBut(event: MouseEvent?){
        // label.font = Font(15.0)
        label.text = ""
        val step: Int = slider.value.toInt()
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()
        this.draw.drawNodeWithStandart()
        this.draw.drawEdge()
        this.draw.drawText()
        obj = Kosaraju(label, draw.graph)
        job = GlobalScope.launch(Dispatchers.Main){
            try {
                obj.startForStep(draw, FrontPane, step)
            }
            catch (er: CancellationException){
                println("Step by step is cancelled")
                obj.graph.graph.forEach { it.visited = false }
                obj.graph.order.clear()
            }
        }.also {
            it.invokeOnCompletion {  }
        }
    }

    @FXML
    fun initialize(event: MouseEvent?) {
        this.draw = Drawablegraph(FrontPane, n, m)
    }
}
