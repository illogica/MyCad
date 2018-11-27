package com.illogica.mycad

import com.illogica.mycad.graph.Pointer
import com.illogica.mycad.graph.SheetA4
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.Line


class ContentManager {

    // Components of the drawing area
    Pane pane

    Pane root
    Pane foreground // for example the pointer or axis
    Pane playground // the actual drawing area
    Pane background // for example the grid


    Line line

    Pointer pointer

    SheetA4 sheetA4


    ContentManager(Pane p){
        pane = p
        println "Correctly initialized"
        println "Pane size is " + pane.height + " - " + pane.width
        pane.setBackground(new Background(new BackgroundFill(Settings.getColor(Settings.KEY_DA_BACKGROUND), null, null)))

        root = new Pane()        // The parent of everything drawn
        foreground = new Pane()  // the front
        playground = new Pane()  // the middle
        background = new Pane()  // the back


        line = new Line()
        line.startX = 0
        line.startY = 0
        line.endX = 100
        line.endY = 100
        line.setStrokeWidth(3.0)
        line.setStroke(Color.GREEN)

        pointer = new Pointer()
        sheetA4 = new SheetA4()

        foreground.getChildren().add(pointer)
        playground.getChildren().add(line)
        background.getChildren().add(sheetA4)

        root.getChildren().add(background)
        root.getChildren().add(playground)
        root.getChildren().add(foreground)

        pane.getChildren().add(root)
    }

    void move(double x, double y){
        root.setTranslateX(x)
        root.setTranslateY(y)
    }
}
