package com.Hussin.Auto_Backup;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Auto_Backup extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Auto_Backup.class.getResource("Home-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ultimate Auto-Backup");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        FXTrayIcon icon = new FXTrayIcon(stage, getClass().getResource("/com/Hussin/Auto_Backup/folder.png"));
        icon.show();
        icon.addExitItem("Exit",e -> {System.exit(0);});

        stage.show();

       denyClose(stage);

    }

    private void denyClose(Stage stage) {
        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}