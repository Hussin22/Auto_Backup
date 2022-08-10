package com.Hussin.Auto_Backup;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class read_Proprety {


    public String readValue(String Value) throws IOException {
        FileReader pread = new FileReader("C:\\Users\\Public\\Documents\\Auto_Backup\\config.properties");
        Properties prop = new Properties();
        prop.load(pread);
      return  prop.getProperty(Value);
    }
}
