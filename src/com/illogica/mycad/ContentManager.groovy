package com.illogica.mycad

import com.illogica.mycad.graph.Pointer
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Line


class ContentManager {

    Line line

    Pointer pointer


    ContentManager(Pane pane){
        println "Correctly initialized"
        println "Pane size is " + pane.height + " - " + pane.width
        pane.setBackground(new Background(new BackgroundFill(Settings.getColor(Settings.KEY_DA_BACKGROUND), null, null)))


        line = new Line()
        line.startX = 0
        line.startY = 0
        line.endX = 100
        line.endY = 100
        line.setStrokeWidth(3.0)
        line.setStroke(Color.GREEN)

        pointer = new Pointer()

        pane.getChildren().add(line)
        pane.getChildren().add(pointer)

    }
}
