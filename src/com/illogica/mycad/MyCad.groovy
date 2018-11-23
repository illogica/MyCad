package com.illogica.mycad

import com.illogica.mycad.controllers.MainController
import com.illogica.mycad.i18n.I18n
import com.illogica.mycad.input.EventManager
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import javafx.stage.Stage

import static com.illogica.mycad.i18n.I18n.tr

class MyCad {

    Scene scene
    FXMLLoader loader
    BorderPane rootPane
    EventManager eventManager
    ContentManager contentManager

    MyCad(Stage primaryStage){

        // Initialize Settings
        Settings.init()
        //Settings.putColor(Settings.KEY_DA_BACKGROUND, Color.GREY) // delete me
        Settings.putDouble(Settings.KEY_DA_POINTER_SIZE, 40.0)
        Settings.putDouble(Settings.KEY_DA_POINTER_WIDTH, 1.0)
        Settings.putColor(Settings.KEY_DA_POINTER_COLOR, Color.WHITESMOKE)

        // Startup the EventManager
        eventManager = new EventManager()
        eventManager.myCad = this

        //Initialize localization support
        I18n.generateLocaleFile("en") // can be deleted later
        I18n.generateLocaleFile("it") // can be deleted later

        loader = new FXMLLoader()
        rootPane = loader.load(new FileInputStream("fxml/main.fxml"))

        // Give a reference to MyCad instance to the main window controller
        def mainController = (MainController)loader.getController()
        mainController.myCad = this

        // Give the ContentManager access to the drawing area
        contentManager = new ContentManager(mainController.paneMain)

        //Show the window
        primaryStage.setTitle(tr("Illogica Mycad"))
        scene = new Scene(rootPane, 800, 600)
        primaryStage.setScene(scene)
        primaryStage.show()
    }

    void quit(){
        System.exit(0)
    }
}
