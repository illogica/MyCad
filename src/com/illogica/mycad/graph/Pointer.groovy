package com.illogica.mycad.graph

import com.illogica.mycad.Settings
import javafx.scene.layout.Region
import javafx.scene.shape.Line

class Pointer extends Region{

    double offset
    double size

    private Line lineV
    private Line lineH

    Pointer(){
        offset = 0
        size = Settings.getDouble(Settings.KEY_DA_POINTER_SIZE)

        lineV = new Line(0 + offset, size/2 + offset, 0 + offset, -size/2 + offset)
        lineV.setStroke(Settings.getColor(Settings.KEY_DA_POINTER_COLOR))
        lineV.setStrokeWidth(Settings.getDouble(Settings.KEY_DA_POINTER_WIDTH))

        lineH = new Line(size/2 + offset, 0 + offset, -size/2 + offset, 0 + offset)
        lineH.setStroke(Settings.getColor(Settings.KEY_DA_POINTER_COLOR))
        lineH.setStrokeWidth(Settings.getDouble(Settings.KEY_DA_POINTER_WIDTH))

        this.getChildren().add(lineV)
        this.getChildren().add(lineH)
        this.setMouseTransparent(true)
    }

    void move(Double x, Double y){
        this.setTranslateX(x)
        this.setTranslateY(y)
    }
}
