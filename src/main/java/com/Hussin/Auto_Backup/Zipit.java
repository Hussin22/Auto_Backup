package com.Hussin.Auto_Backup;

import javafx.application.Platform;
import net.lingala.zip4j.ZipFile;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Zipit implements Job {

    //read proprety from conig file class
    read_Proprety rp ;
    //set proprety from conig file class

    Set_Proprety Sp;

    @Override
    public void execute(JobExecutionContext jobExecutionContext ) throws JobExecutionException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    rp  = new read_Proprety();
                    String Name = File_Name();
                    String Dispath = rp.readValue("D_Path")+"\\"+Name+".zip";

                    new ZipFile(Dispath).addFolder(new File(rp.readValue("Path")));
                    System.out.println("Zip created !!");


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void setpath(String path) throws IOException {
        Sp = new Set_Proprety();
        Sp.Set_Prop("Path",path);
    }


    public String File_Name(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH__mm");
        LocalDateTime now = LocalDateTime.now();
        return  dtf.format(now)+"_Backup";
    }
}
