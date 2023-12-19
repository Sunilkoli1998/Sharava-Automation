package com.data.Shrava_data;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class PropertiesFile {


        public static Properties prop = new Properties();


    public static void readPropertiesFIle() throws Exception {

        String path = new File("src/main/java/com/data/Shrava_data/login.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));

    }



    public static void readSummaryPropertiesFIle() throws Exception {

        String path = new File("src/main/java/com/data/Shrava_data/Summarytab.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));

    }
    public static void readRbcPropertiesFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/rbc.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }
    
    public static void readShonitListReportFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/shonitListReport.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }
    public static void readMandaraHomePropertiesFile() throws Exception{
         String path = new File("src/main/java/com/data/Shrava_data/mandarahomeconfig.properties")
                 .getAbsolutePath();
         prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
     }
    
    public static void readShonitPropertiesFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/shonitReportPage.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }

    public static void readVisualPropertiesFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/visualconfig.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }

    public static void readSearchFilterFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/searchfilter.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));

    }
    public static void readMicroscopicViewFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/microscopicview.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }

    public static void readBloodCellsFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/bloodcells.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }

    public static void readPageLoadFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/pageload.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }
    
    public static void readCommentPropertiesFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/comment.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }
    
    public static void readApproveRejectFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/rejectapprove.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }
    public static void readSystemReleaseFile() throws Exception{
        String path = new File("src/main/java/com/data/Shrava_data/systemRelease.properties")
                .getAbsolutePath();
        prop.load(new InputStreamReader(new FileInputStream(path), "UTF8"));
    }

}
