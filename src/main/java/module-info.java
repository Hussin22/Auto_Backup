module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires zip4j;
    requires quartz;
    requires FXTrayIcon;


    opens com.Hussin.Auto_Backup to javafx.fxml;
    exports com.Hussin.Auto_Backup;
}