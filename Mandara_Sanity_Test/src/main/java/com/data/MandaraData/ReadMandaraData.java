package com.data.MandaraData;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadMandaraData {
	
    public static Map<String, String> ShonitRefData =new HashMap<String, String>();
    
    public Map<String, String> ReadCSVFile(String filename) throws Exception {
  
        String line = "";
        String curDir = System.getProperty("user.dir");
        String temppath="/src/main/java/com/data/MandaraData/";
        //System.out.println("file location : "+curDir+temppath+filename);
        try {
        	BufferedReader br = new BufferedReader(new FileReader(curDir+temppath+filename));
            while ((line = br.readLine()) != null) {
                String[] rowdata = line.split(",");
                ShonitRefData.put(rowdata[0], rowdata[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(ShonitRefData);
        return ShonitRefData;
    }
 
    public static void main(String[] args) throws Exception{
    	ReadMandaraData rmd=new ReadMandaraData();
    	ShonitRefData=rmd.ReadCSVFile("Shonit.csv");
    }
        

}
