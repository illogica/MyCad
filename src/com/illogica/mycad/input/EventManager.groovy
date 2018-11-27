package com.illogica.mycad.input

import com.illogica.mycad.MyCad
import javafx.scene.input.MouseEvent
import javafx.scene.input.ScrollEvent

class EventManager {

    MyCad myCad

    void handleMouseClick(MouseEvent event){
        println "Click: " + event
    }

    void handleMouseMove(MouseEvent event){
        myCad.contentManager.pointer.move(event.x, event.y)
    }

    void handleMouseScroll(ScrollEvent event){
        println "Scroll: " + event
    }

    void handleResize(Double height, Double width){
        //println "New size: " + height + ", " + width
    }

    void handleMouseDrag(MouseEvent event){
        myCad.contentManager.move(event.x, event.y)
    }
}
