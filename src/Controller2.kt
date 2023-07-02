package com.example.demo

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import java.net.URL
import java.util.*

class Controller2 {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var AddEdge: Button

    @FXML
    private lateinit var AddNode: Button

    @FXML
    private lateinit var BACK: VBox

    @FXML
    private lateinit var Button: Button

    @FXML
    private lateinit var Pane: Pane

    @FXML
    private lateinit var label: Text


    var elements = ArrayList<Pair<Circle, Text>>()
    var draggableMaker = DraggableMaker()

    @FXML
    fun AddEdge(event: ActionEvent) {
        createEdge()
    }
    @FXML
    fun AddNode(event: ActionEvent) {
            createElement(Color.DARKGREY)
    }

    @FXML
    fun BackToChoice(event: ActionEvent) {
        val stage: Stage? = Button.scene.window as Stage?

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("sample.fxml"))
        val scene = Scene(fxmlLoader.load(), 700.0, 480.0)
        stage?.title = "0 Stage. Choice."
        stage?.scene = scene
    }
    var cnt = 0
    fun createElement(color: Color): Circle {

        val circle = Circle(150.0, 150.0, 15.0)
        circle.fill = color

        val text = Text(150.0-circle.radius, 150.0-circle.radius, "$cnt")
        cnt++
        text.fill = Color.RED
        text.font = Font(16.0)
        Pane.children.add(text)


        draggableMaker.makeDraggable(circle, text)
        Pane.children.add(circle)

        elements.add(Pair(circle, text))
        return circle
    }
    fun createEdge(){
        val line = Line(elements[0].first.layoutX, elements[0].first.layoutY, elements[1].first.layoutX, elements[1].first.layoutY)
        line.stroke = Color.WHITE
        line.fill = null
        line.strokeWidth = 2.0
        Pane.children.add(line)

    }
    @FXML
    fun initialize() {


    }

}
