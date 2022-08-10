package com.Hussin.Auto_Backup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @javafx.fxml.FXML
    private Button Start_Bk;
    @javafx.fxml.FXML
    private CheckBox Daily_Check;
    @javafx.fxml.FXML
    private ComboBox Day_Month;
    @javafx.fxml.FXML
    private ComboBox Hour;
    @javafx.fxml.FXML
    private CheckBox Week_Check;
    @javafx.fxml.FXML
    private TextField D_Path;
    @javafx.fxml.FXML
    private CheckBox Hour_Check;
    @javafx.fxml.FXML
    private CheckBox Auto_Check;
    @javafx.fxml.FXML
    private CheckBox Month_Check;
    @javafx.fxml.FXML
    private ComboBox Minutes;
    @javafx.fxml.FXML
    private ComboBox Day_week;
read_Proprety rp;
Set_Proprety Sp;



@FXML public void Close(){System.exit(0);}
    @FXML
    void daybox() {
        Week_Check.setSelected(false);
        Month_Check.setSelected(false);
        Hour_Check.setSelected(false);
        Daily_Check.setSelected(true);
    }

    @FXML
    void hourbox() {
        Week_Check.setSelected(false);
        Month_Check.setSelected(false);
        Hour_Check.setSelected(true);
        Daily_Check.setSelected(false);


    }

    @FXML
    void monthbox() {
        Week_Check.setSelected(false);
        Daily_Check.setSelected(false);
        Hour_Check.setSelected(false);
        Month_Check.setSelected(true);
    }
    @FXML
    void weekbox() {
Week_Check.setSelected(true);
Month_Check.setSelected(false);
Daily_Check.setSelected(false);
Hour_Check.setSelected(false);
    }

    @javafx.fxml.FXML
    public void setPath() {
        Select_Directory SD = new Select_Directory();
        D_Path.setText(SD.select_Dir());

    }
    @javafx.fxml.FXML
    public void Set_Config() throws IOException {
      Sp = new Set_Proprety();

      Sp.Set_Prop("Hour",Hour.getValue().toString());
      Sp.Set_Prop("Minutes",Minutes.getValue().toString());
      Sp.Set_Prop("Day_Month",Day_Month.getValue().toString());
      Sp.Set_Prop("Day_Week",ConvertDay(Day_week.getValue().toString()));
      Sp.Set_Prop("D_Path",D_Path.getText());
      Sp.Set_Prop("Auto_Start",String.valueOf(Auto_Check.isSelected()));


      Sp.Set_Prop("IsWeekly",String.valueOf(Week_Check.isSelected()));
      Sp.Set_Prop("IsMounthly",String.valueOf(Month_Check.isSelected()));
      Sp.Set_Prop("IsDaily",String.valueOf(Daily_Check.isSelected()));
      Sp.Set_Prop("isHourly",String.valueOf(Hour_Check.isSelected()));

      Alerty al = new Alerty();
      al.errAlert("Setting Saved!","All settings saved application will restart now...");

      restartApp();



    }

    private void restartApp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home-View.fxml"));
        Stage pstage = new Stage();
        Scene scene  = new Scene(root);
        pstage.setScene(scene);
        pstage.initStyle(StageStyle.UNDECORATED);
        //hide current window
        Stage CurrentWin = (Stage)Auto_Check.getScene().getWindow();
        CurrentWin.close();
        System.exit(0);
        pstage.show();
    }

    private String ConvertDay(String Day) {
                int day;
       switch (Day){

           case "Sunday":
               day = 1;
               break;
           case "Monday":
               day = 2;
               break;
           case "Tuesday":
               day = 3;
               break;
           case "Wednesday":
               day = 4;
               break;
           case "Thursday":
               day = 5;
               break;
           case "Friday":
               day = 6;
               break;
           case "Saturday":
               day = 7;
               break;
           default:
               day=1;
       }

        return String.valueOf(day);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rp = new read_Proprety();
        try {
            setcombox();
            D_Path.setText(rp.readValue("D_Path"));
            Week_Check.setSelected(Boolean.valueOf(rp.readValue("IsWeekly")));
            Month_Check.setSelected(Boolean.valueOf(rp.readValue("IsMounthly")));
            Hour_Check.setSelected(Boolean.valueOf(rp.readValue("isHourly")));
            Daily_Check.setSelected(Boolean.valueOf(rp.readValue("IsDaily")));
            Auto_Check.setSelected(Boolean.valueOf(rp.readValue("Auto_Start")));
            Hour.setValue(rp.readValue("Hour"));
            Day_week.setValue(rp.readValue("Day_Week"));
            Minutes.setValue(rp.readValue("Minutes"));
            Day_Month.setValue(rp.readValue("Day_Month"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setcombox() {
        //set Hour
        ObservableList<String> data = FXCollections.observableArrayList("1", "2","3", "4","5", "6","7", "8","9", "10","11", "12","13", "14"
        ,"15", "16","17", "18","19", "20","21", "22","23", "00");
        Hour.setItems(data);
        Hour.getSelectionModel().select("10");
        //set  day - week
        ObservableList<String> dataDay = FXCollections.observableArrayList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        Day_week.setItems(dataDay);
        Day_week.getSelectionModel().select("Monday");
        //set  day - Month

        ObservableList<String> dataDay_M = FXCollections.observableArrayList();
        int M = 0;
        while (M<32){
            dataDay_M.add(String.valueOf(M));
            M++;
        }
        Day_Month.setItems(dataDay_M);
        Day_Month.getSelectionModel().select("1");

        //set  day - Month

        ObservableList<String> dataMin = FXCollections.observableArrayList();
        int Min = 1;
        while (Min<61){
            dataMin.add(String.valueOf(Min));
            Min++;
        }
        Minutes.setItems(dataMin);
        Minutes.getSelectionModel().select("1");

    }

}
