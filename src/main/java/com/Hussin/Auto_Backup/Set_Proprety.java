package com.Hussin.Auto_Backup;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Set_Proprety {

    public void Set_Prop(String Parm, String path) throws IOException {
        FileReader in = new FileReader("C:\\Users\\Public\\Documents\\Auto_Backup\\config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        FileOutputStream out = new FileOutputStream("C:\\Users\\Public\\Documents\\Auto_Backup\\config.properties");
        props.setProperty(Parm, path);
        props.store(out, null);
        out.close();
    }
}
