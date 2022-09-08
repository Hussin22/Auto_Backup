package com.Hussin.Auto_Backup;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.quartz.SchedulerException;
import com.dustinredmond.fxtrayicon.FXTrayIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
public class HomeController implements Initializable {
    Boolean isWeekly;
    Boolean isDaily;
    Boolean isMonthly;
    Boolean isHourly;
    int Hour;
    int Minutes;
    int Day_Week1;
    int Day_Month;
    read_Proprety rp;
    Set_Proprety sp;
    Select_Directory SD;
    @FXML
    private TextField Path;
    @FXML
    private CheckBox Auto_Check;

    @FXML
    private Button Stop_Btn;
    @FXML
    private Button Start_B;


    @FXML
    void setPath() {
        SD = new Select_Directory();
        Path.setText(SD.select_Dir());
    }
    @FXML
    void Start_Bk() throws SchedulerException, IOException {
        Alerty al = new Alerty();
//        al.errAlert("Backup is started", "backup is started please Hide application by click close button!");
        if (isWeekly) {
            Day_Week D_week = new Day_Week();
            D_week.weekly(Path.getText(), Hour, Minutes, Day_Week1);
        } else if (isDaily){
            Daily Dl = new Daily();
            Dl.Daily(Path.getText(), Hour, Minutes);
        }
        else if (isMonthly){
            Monthly ML = new Monthly();
            ML.Monthly(Path.getText(), Hour, Minutes, Day_Week1);
        }
        else if (isHourly){

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                Hourly HL = new Hourly();
                                HL.Hourly(Path.getText());
                            } catch (SchedulerException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    5000
            );

        }
        Start_B.setVisible(false);
        Stop_Btn.setVisible(true);
        Hide_Tray();
    }
    @FXML
    public void Stop_backup() throws SchedulerException {
        Day_Week weekpro = new Day_Week();
        weekpro.stoppro();
        Start_B.setVisible(true);
        Stop_Btn.setVisible(false);
    }
    @FXML
    void Setting_Set() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage pstage = new Stage();
        Scene scene  = new Scene(root);
        pstage.setScene(scene);
        pstage.initStyle(StageStyle.UNDECORATED);
        //hide current window
        Stage CurrentWin = (Stage)Path.getScene().getWindow();
        CurrentWin.close();
        pstage.show();
    }
    @FXML
    void auto_EV() throws IOException {
        sp = new Set_Proprety();
sp.Set_Prop("Auto_Start",String.valueOf(Auto_Check.isSelected()));
    }
    @FXML
    void Hide_Tray() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = (Stage)Path.getScene().getWindow();

                stage.hide();
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Stop_Btn.setVisible(false);
            CheckSetting();

            rp = new read_Proprety();
            Path.setText(rp.readValue("Path"));

            if(Boolean.valueOf(rp.readValue("Auto_Start"))==true){
                Start_Bk();
            }
        } catch (IOException | SchedulerException e) {
            e.printStackTrace();
        }
    }


    private void CheckSetting() throws IOException {
read_Proprety rp = new read_Proprety();

        isWeekly    = Boolean.valueOf((rp.readValue("IsWeekly")));
        isDaily    = Boolean.valueOf((rp.readValue("IsDaily")));
        isMonthly = Boolean.valueOf((rp.readValue("IsMonthly")));
        isHourly = Boolean.valueOf((rp.readValue("isHourly")));
        Hour = Integer.parseInt(rp.readValue("Hour"));
        Minutes = Integer.parseInt(rp.readValue("Minutes"));
        Day_Week1 = Integer.parseInt(rp.readValue("Day_Week"));
        Day_Month = Integer.parseInt(rp.readValue("Day_Month"));



    }

    @FXML
    public void github() throws IOException, URISyntaxException { Desktop.getDesktop().browse(new URL("https://github.com/Hussin22").toURI());}
    @FXML
    public void Linkdin() throws IOException, URISyntaxException { Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/hussin-tsouli-1322361a3/").toURI());}
    @FXML
    public void cv() throws IOException, URISyntaxException { Desktop.getDesktop().browse(new URL("https://hussin22.github.io/My-Portoflio/").toURI());}



}