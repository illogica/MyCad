package com.illogica.mycad;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MyCad(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
