package com.Hussin.Auto_Backup;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Select_Directory {
    public String select_Dir(){
        final Stage primaryStage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("C:\\Users\\Public\\Documents"));
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        return selectedDirectory.getAbsolutePath();
    }
}
