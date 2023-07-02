package com.example.demo

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.text.Text
import javafx.stage.Stage

class Controller {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var BACK: VBox

    @FXML
    private lateinit var ButtonFile: Button

    @FXML
    private lateinit var ButtonKeyboard: Button

    @FXML
    private lateinit var Pane: Pane

    @FXML
    private lateinit var label: Text

    @FXML
    fun ReadFromFile(event: MouseEvent) {
        val stage: Stage? = ButtonFile.scene.window as Stage?

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("sample1.fxml"))
        val scene = Scene(fxmlLoader.load(), 700.0, 480.0)
        stage?.title = "2 Stage. File."
        stage?.scene = scene
    }

    @FXML
    fun ReadFromKlava(event: MouseEvent) {
        val stage: Stage? = ButtonFile.scene.window as Stage?

        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("sample2.fxml"))
        val scene = Scene(fxmlLoader.load(), 700.0, 480.0)
        stage?.title = "1 Stage. Keyboard."
        stage?.scene = scene
    }

    @FXML
    fun initialize() {
    }


}
