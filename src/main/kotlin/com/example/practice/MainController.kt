package com.example.practice

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSlider
import javafx.fxml.FXML
import javafx.geometry.Point2D
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.awt.MouseInfo
import java.net.URL
import java.rmi.AccessException
import java.util.*
import kotlin.coroutines.cancellation.CancellationException


class MainController {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var Downlabel: Label

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
    @FXML
    private lateinit var GenBut: JFXButton

    @FXML
    private lateinit var MoveBut: JFXButton

    private lateinit var draw: Drawablegraph

    private lateinit var obj: Kosaraju
    private val list_lines = ArrayList<Triple<Line, Node, Node>>()
    private var job: Job = Job()
    private var n: Int = 5
    private var m: Int = 7

    @FXML
    fun LoadBut(event: MouseEvent?) {
        MoveBut.isDisable = false
        label.text = ""
        Downlabel.text = ""
        if (job.isActive)
            job.cancel()
        draw = Drawablegraph(FrontPane)
        FrontPane.children.clear()
        val loader = Loader()
        draw.graph.clearGraph()
        draw.graph.graph = loader.loadFromFile("saved_graph.json")
        obj = Kosaraju(Downlabel, label, draw.graph)
        if(draw.graph.graph.size > 0 && draw.graph.graph[0].circle.centerX.toInt() == 0 && draw.graph.graph[1].circle.centerY.toInt() == 0)
            draw.drawNode()
        else
            draw.drawNodeWithStandart()
        draw.drawEdge()
        draw.drawText()
        Downlabel.text = " - The graph from the file is loaded."

    }

    @FXML
    fun SaveBut(event: MouseEvent?){
        label.text = ""
        Downlabel.text = ""
        MoveBut.isDisable = false
        if (job.isActive) {
            job.cancel()
            FrontPane.children.clear()
            draw.drawNode()
            draw.drawEdge()
            draw.drawText()
        }
        if(draw.graph.graph.size > 0){
            val saver = Saver()
            saver.saveToFile(draw.graph.graph)
            Downlabel.text = " - The current graph  is saved."
        }
    }

    @FXML
    fun ClearClicked(event: MouseEvent?) {
        MoveBut.isDisable = false
        label.text = ""
        Downlabel.text = ""
        if (job.isActive)
            job.cancel()
        //obj = Kosaraju(draw.graph)
        try{draw.graph.clearGraph()}
        catch (er: kotlin.UninitializedPropertyAccessException){
            return
        }
        FrontPane.children.clear()
    }

    @FXML
    fun GenerateBut(event: MouseEvent) {
        MoveBut.isDisable = false
        draw = Drawablegraph(FrontPane, n, m)
        label.text = ""
        if (job.isActive)
            job.cancel()
        if (WindowForInput.text != "") {
            val Node_Edge = WindowForInput.text
            if (Node_Edge.split(' ').size in 2..3 && Node_Edge.split(' ')[0].toInt() > 0 && Node_Edge.split(' ')[1].toInt() >= 0) {
                this.n = Node_Edge.split(' ')[0].toInt()
                this.m = Node_Edge.split(' ')[1].toInt()
                draw = Drawablegraph(FrontPane, n, m)
                Downlabel.text = ""
                if (m > n * (n - 1)) {
                    Downlabel.text = " - Invalid data! It has change to n and n * (n-1)."
                }
                WindowForInput.clear()
            } else {
                Downlabel.text = "Enter correct values."
                WindowForInput.clear()
                return
            }
        }
        obj = Kosaraju(Downlabel, label, draw.graph)
        FrontPane.children.clear()
        this.draw.drawNode()
        this.draw.drawEdge()
        this.draw.drawText()
    }

    @FXML
    fun StartAlgorithm(event: MouseEvent?) {
        label.text = ""
        Downlabel.text = ""
        MoveBut.isDisable = false
        try {
            obj.graph.graph.forEach { it.visited = false }
            if (job.isActive) {
                job.cancel()


                obj.graph.order.clear()
                println("job is done!")
            }

            obj = Kosaraju(Downlabel, label, draw.graph)
            obj.start()
        }
        catch (er: kotlin.UninitializedPropertyAccessException){
            return
        }
    }

    @FXML
    fun DeleteClick(event: MouseEvent?) {
        label.text = ""
        Downlabel.text = ""
        MoveBut.isDisable = false
        if (job.isActive)
            job.cancel()
        FrontPane.children.clear()

        try {
            this.draw.drawNodeWithStandart()
            this.draw.drawEdge()
            this.draw.drawText()
            for (node in draw.graph.graph) {
                for (line in node.List_of_Lines) {
                    list_lines.add(Triple(line.first, node, line.second))
                }
            }
            for (line in list_lines) {
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
                    })
                }
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
        }catch (er: kotlin.UninitializedPropertyAccessException){
            return
        }

    }

    fun stepBut(event: MouseEvent?){
        MoveBut.isDisable = false
        label.text = ""
        Downlabel.text = ""
        val step: Int = slider.value.toInt()
        if (job.isActive)
            job.cancel()

        FrontPane.children.clear()
        try {
            this.draw.drawNodeWithStandart()
            this.draw.drawEdge()
            this.draw.drawText()
            obj = Kosaraju(Downlabel, label, draw.graph)
            job = GlobalScope.launch(Dispatchers.Main) {
                try {
                    obj.startForStep(draw, FrontPane, step)
                } catch (er: CancellationException) {
                    println("Step by step is cancelled")
                    obj.graph.graph.forEach { it.visited = false }
                    obj.graph.order.clear()
                }
            }.also {
                it.invokeOnCompletion { }
            }
        }catch (er: kotlin.UninitializedPropertyAccessException){
            return
        }

    }
    @FXML
    fun initialize() {
    //    this.draw = Drawablegraph(FrontPane, n, m)
    }
    var tmp_circle: Circle? = Circle(0.0, 0.0, 20.0)
    var x: Double = 0.0
    var y: Double = 0.0

    @FXML
    fun TestForPane(event: MouseEvent) {
        if(MoveBut.isDisable){
            val p = MouseInfo.getPointerInfo().location
            val point2D: Point2D = Point2D(event.x, event.y)
            if(Circle(this.x, this.y, 20.0).contains(point2D) || MoveBut.contains(point2D)){
                return
            }
            FrontPane.children.clear()
            tmp_circle?.centerX  = event.x
            tmp_circle?.centerY = event.y

            for (el in draw.graph.graph) {
                FrontPane.children.add(el.circle)
            }
            this.draw.drawEdge()
            this.draw.drawText()

        }
        tmp_circle = null
        MoveBut.isDisable = false
    }
    @FXML
    fun MoveClicked(event: MouseEvent) {
        try {
            if (draw.graph.graph.size > 0) {
                MoveBut.isDisable = true
                for (node in draw.graph.graph) {
                    node.circle.setOnMouseClicked { mouseEvent ->
                        (run {
                            this.x = node.circle.centerX
                            this.y = node.circle.centerY
                            this.tmp_circle = node.circle
                        })
                    }
                    node.txt.setOnMouseClicked { mouseEvent ->
                        (run {
                            this.x = node.circle.centerX
                            this.y = node.circle.centerY
                            this.tmp_circle = node.circle
                        })
                    }
                }
            }
        }catch (er: kotlin.UninitializedPropertyAccessException){
            return
        }

    }
}
