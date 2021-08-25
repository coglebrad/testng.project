package com.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class Configuration {
    private Properties props = System.getProperties();
    public void loadProperties(){
        //System.out.println("Environment Name: " + System.getProperty("env"));
        System.setProperty("env","test");
        try{
            Path propFile = Paths.get("src/main/resources/",System.getProperty("env")+".properties");
            System.out.println(propFile);
            props.load(new FileInputStream(new File(propFile.toString())));
            Set<String> keys = props.stringPropertyNames();
            for (String key : keys){
                System.setProperty(key,props.getProperty(key));

            }

        } catch (Exception ex){
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
