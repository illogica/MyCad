package com.illogica.mycad.controllers

import com.illogica.mycad.MyCad
import javafx.beans.value.ChangeListener
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Cursor
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.Pane
import javafx.scene.control.Button
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import javafx.scene.shape.Rectangle

import static com.illogica.mycad.i18n.I18n.tr


class MainController implements Initializable{
    public MyCad myCad

    @FXML
    TextField txtConsole

    @FXML
    Pane paneMain

    @FXML
    Button btnEnter

    @FXML
    private MenuBar menuBar

    @FXML
    private Menu menuFile

    @FXML
    private MenuItem menuClose

    @FXML
    private Menu menuEdit

    @FXML
    private MenuItem menuSettings

    @FXML
    private Menu menuHelp

    @FXML
    private MenuItem menuAbout


    @Override
    void initialize(URL location, ResourceBundle resources) {
        btnEnter.text = tr("Enter")

        menuFile.text = tr("File")
        menuClose.text = tr("Close")
        menuEdit.text = tr("Edit")
        menuSettings.text = tr("Settings")
        menuHelp.text = tr("Help")
        menuAbout.text = tr("About")

        Rectangle clipRectangle = new Rectangle()
        paneMain.setClip(clipRectangle)

        def resizeListener = {observable, oldValue, newValue ->

            // Clip the pane view
            clipRectangle.height = paneMain.height
            clipRectangle.width = paneMain.width
            //clipRectangle.layoutX = paneMain.layoutX
            //clipRectangle.layoutY = paneMain.layoutY

            // Handle resize events
            myCad.eventManager.handleResize(paneMain.width, paneMain.height)
        } as ChangeListener

        paneMain.widthProperty().addListener(resizeListener)
        paneMain.heightProperty().addListener(resizeListener)
        paneMain.setCursor(Cursor.NONE)
    }


    @FXML
    void btnEnter_onClick(MouseEvent event) {

    }

    @FXML
    void onMenuAbout(ActionEvent event) {

    }

    @FXML
    void onMenuClose(ActionEvent event) {
        myCad.quit()
    }

    @FXML
    void onMenuSettings(ActionEvent event) {

    }

    @FXML
    void onPaneMainMouseClick(MouseEvent event) {
        myCad.eventManager.handleMouseClick(event)
    }

    @FXML
    void onPaneMainMouseMove(MouseEvent event) {
        myCad.eventManager.handleMouseMove(event)
    }

    @FXML
    void onPaneMainScroll(ScrollEvent event) {
        myCad.eventManager.handleMouseScroll(event)
    }

    @FXML
    void onPaneMainMouseEnter(MouseEvent event) {
        //println "Entered mouse: " + event
        //paneMain.setCursor(Cursor.NONE)
    }

    @FXML
    void onPaneMainMouseExit(MouseEvent event) {
        //println "Exit mouse: " + event
        //paneMain.setCursor(Cursor.TEXT)
    }
}