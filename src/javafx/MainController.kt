package com.example.practice

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Line
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
    private lateinit var ClearBut: Button

    @FXML
    private lateinit var DelBut: Button

    @FXML
    private lateinit var LoadBut: Button

    @FXML
    private lateinit var SaveBut: Button

    @FXML
    private lateinit var VertLine: Line

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    fun LoadBut(event: MouseEvent?) {

    }

    @FXML
    fun initialize() {
    }

}
