package com.illogica.mycad.graph

import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class SheetA4 extends Region{
    double HEIGHT = 210.0
    double WIDTH = 297.0

    private Rectangle rect

    SheetA4(){
        rect = new Rectangle(0,0, WIDTH, HEIGHT)
        rect.setFill(Color.TRANSPARENT)
        rect.setStroke(Color.BLACK)
        rect.setStrokeWidth(2.0)
        this.getChildren().add(rect)
    }
}
