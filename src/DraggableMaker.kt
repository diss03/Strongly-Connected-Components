package com.example.demo

import javafx.scene.Node

class DraggableMaker{
    private var mouseAnchorX: Double = 0.0
    private var mouseAnchorY: Double = 0.0

    fun makeDraggable(node: Node, txt: Node){

        node.setOnMousePressed { mouseEvent -> (run {
            mouseAnchorX = mouseEvent.x
            mouseAnchorY = mouseEvent.y
        }) }

        node.setOnMouseDragged{ mouseEvent -> (run {
            node.layoutX = mouseEvent.sceneX - mouseAnchorX
            node.layoutY = mouseEvent.sceneY - mouseAnchorY
            txt.layoutX = mouseEvent.sceneX - mouseAnchorX
            txt.layoutY = mouseEvent.sceneY - mouseAnchorY
        }) }




    }

}