package com.example.practice

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import kotlinx.coroutines.*


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("sample.fxml"))
        val scene = Scene(fxmlLoader.load(), 1200.0, 820.0)
        stage.title = "Kosaraju"
        stage.scene = scene
        stage.show()
    }
}
fun main(){
    Application.launch(HelloApplication::class.java)
}
