package com.example.practice

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.text.Font
import java.net.URL
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

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
    fun LoadBut(event: MouseEvent?) {
    }

    @FXML
    fun ClearClicked(event: MouseEvent?) {
    }

    @FXML
    fun GenerateBut(event: MouseEvent) {
        val draw = Drawablegraph(FrontPane)
        draw.drawNode()
        draw.drawEdge()
        draw.drawText()
    }

    @FXML
    fun initialize() {
    }

}
