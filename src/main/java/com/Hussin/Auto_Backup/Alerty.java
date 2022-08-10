package com.Hussin.Auto_Backup;
import javafx.scene.control.Alert;



public class Alerty {

    public void errAlert(String Title,String Content){
Alert AL = new Alert(Alert.AlertType.INFORMATION);
AL.setTitle(Title);
AL.setContentText(Content);
AL.showAndWait();

    }
}
