package com.data;

import com.opencsv.CSVReader;
import com.data.Shrava_data.PropertiesFile;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class readShonitReportcsv {

    public Properties props;


    public HashMap<String, String> readcsvdata() throws Exception {

        PropertiesFile.readShonitPropertiesFile();
        props = PropertiesFile.prop;
        CSVReader reader = new CSVReader(new FileReader("src/main/java/com/data/Shonit.csv"));
        HashMap<String, String> map = new HashMap<String, String>();
        String[] nextline;
        String[] header = reader.readNext();

        while ((nextline = reader.readNext()).length > 0)
            if (nextline[0].equals(props.getProperty("searchAnalysisID")))
                break;
        for (int j = 0; j < nextline.length; j++)
            map.put(header[j], nextline[j]);

        return map;
    }

    //fetches WBC report metrics data from csv file
    public List<String> getReportMetricsData() throws Exception {
        HashMap<String, String> map = readcsvdata();


        List<String> reportMetricslist = new ArrayList<String>();

        String NEUT = "NEUT" + " " + map.get("Neut_TC") +" " + map.get("Neut_tc_Ref").replaceAll("\\s","")+ " /µL " + map.get("%Neut") + "%";
        reportMetricslist.add(NEUT);
        String LYMPH = "LYMPH" + " " + map.get("Lymph_TC") + " "+ map.get("Lymph_tc_Ref").replaceAll("\\s","")+ " /µL " +map.get("%Lymph") + "%";
        reportMetricslist.add(LYMPH);
        String MONO = "MONO" + " " + map.get("Mono_TC") + " "+ map.get("Mono_tc_Ref").replaceAll("\\s","") + " /µL " + map.get("%Mono") + "%";
        reportMetricslist.add(MONO);
        String EO = "EO" + " " + map.get("Eoso_TC") + " "+ map.get("Eoso_tc_Ref").replaceAll("\\s","") + " /µL " + map.get("%Eoso") + "%";
        reportMetricslist.add(EO);
        String BASO = "BASO" + " " + map.get("Baso_TC") + " "+ map.get("Baso_tc_Ref").replaceAll("\\s","") + " /µL " + map.get("%Baso") + "%";
        reportMetricslist.add(BASO);

        return reportMetricslist;
    }

    //fetches WBC Absolute count data from csv file
    public List<String> getABSData() throws Exception {
        HashMap<String, String> map = readcsvdata();

        List<String> ABClist = new ArrayList<String>();

        String NEUT = "NEUT" + " " + map.get("#Neut") + " " + map.get("%Neut") + " %";
        ABClist.add(NEUT);
        String LYMPH = "LYMPH" + " " + map.get("#Lymph") + " " + map.get("%Lymph") + " %";
        ABClist.add(LYMPH);
        String MONO = "MONO" + " " + map.get("#Mono") + " " + map.get("%Mono") + " %";
        ABClist.add(MONO);
        String EO = "EO" + " " + map.get("#Eoso") + " " + map.get("%Eoso") + " %";
        ABClist.add(EO);
        String BASO = "BASO" + " " + map.get("#Baso") + " " + map.get("%Baso") + " %";
        ABClist.add(BASO);
        String WBCTotal = "TOTAL" + " " + map.get("#WBC") + " " + map.get("%WBC") + " %";
        ABClist.add(WBCTotal);
        String NRBC = "NRBC *" + " " +map.get("#nrbc") + " " + map.get("%nrbc") + " %";
        ABClist.add(NRBC);
        String IG = "IG *" + " " +Math.round(Double.parseDouble(map.get("#Ig"))) + " " + map.get("%Ig") + " %";
        ABClist.add(IG);
        String AtypicalBlast = "Atypical Blast *" + " " + map.get("#Blast") + " " + map.get("%Blast") + " %";
        ABClist.add(AtypicalBlast);
        String Unclassified = "Unclassified" + " " + map.get("#Unclassified_W");
        ABClist.add(Unclassified);
        String Rejected = "Rejected" + " " + map.get("#Rejectes_W");
        ABClist.add(Rejected);

        return ABClist;
    }


    //fetches Platelet Absolute count data from csv file
    public List<String> getPlateletABSData() throws Exception{

        HashMap<String, String> map = readcsvdata();

        List<String> PlateletABSlist = new ArrayList<String>();

        String Platelet = "Platelet " + map.get("#PLT") + " " + map.get("%PLT") + " %";
        PlateletABSlist.add(Platelet);
        String LargePlatelet = "Large Platelet " + map.get("#GPLT") + " " + map.get("%GPLT") + " %";
        PlateletABSlist.add(LargePlatelet);
        String TOTAL = "TOTAL " + map.get("#PLT_Total") + " " + map.get("%PLT_Total") + " %";
        PlateletABSlist.add(TOTAL);
        String PlateletClump = "Platelet Clump " + map.get("#PC");
        PlateletABSlist.add(PlateletClump);

        return PlateletABSlist;
    }

    //fetches RBC Size data from csv file
    public List<String> getRBCSizeData() throws Exception{
        HashMap<String, String> map = readcsvdata();

        List<String> RBCsizelist = new ArrayList<String>();

        String Microcyte = "Microcyte " + map.get("#Micro") + " " + map.get("%Micro") + " %";
        RBCsizelist.add(Microcyte);
        String Normocyte =  "Normocyte " + map.get("#Normo") + " " + map.get("%Normo") + " %";
        RBCsizelist.add(Normocyte);
        String RoundMacrocyte = "Round Macrocyte " + map.get("#RMacro") + " " + map.get("%RMacro") + " %";
        RBCsizelist.add(RoundMacrocyte);
        String OvaloMacrocyte = "Ovalo Macrocyte " + map.get("#OMacro") + " " + map.get("%OMacro") + " %";
        RBCsizelist.add(OvaloMacrocyte);

        return RBCsizelist;
    }

    //fetches RBC Poikilocytes data from csv file
    public List<String> getRBCPoikilocytesData() throws Exception{
        HashMap<String, String> map = readcsvdata();

        List<String> RBCPoikilocytelist = new ArrayList<String>();

        String Normal = "Normal " + map.get("#Normal") + " " + map.get("%Normal") + " %";
        RBCPoikilocytelist.add(Normal);
        String Elliptocyte = "Elliptocyte " + map.get("#Ellipto") + " " + map.get("%Ellipto") + " %";
        RBCPoikilocytelist.add(Elliptocyte);
        String Ovalocyte = "Ovalocyte " + map.get("#Ovalo") + " " + map.get("%Ovalo") + " %";
        RBCPoikilocytelist.add(Ovalocyte);
        String Target= "Target * " + map.get("#Target") + " " + map.get("%Target") + " %";
        RBCPoikilocytelist.add(Target);
        String Teardrop = "Teardrop " + map.get("#Teardrop") + " " + map.get("%Teardrop") + " %";
        RBCPoikilocytelist.add(Teardrop);
        String Echinocyte = "Echinocyte " + map.get("#Echino") + " " + map.get("%Echino") + " %";
        RBCPoikilocytelist.add(Echinocyte);
        String Fragmented = "Fragmented " + map.get("#Fragmented") + " " + map.get("%Fragmented") + " %";
        RBCPoikilocytelist.add(Fragmented);
        String TOTAL = "TOTAL " + map.get("#RBC_Total") + " " + map.get("%RBC_Total") + " %";
        RBCPoikilocytelist.add(TOTAL);

        return RBCPoikilocytelist;
    }

    //fetches RBC Diameter data from csv file
    public List<String> getRBCDiameterData() throws Exception{
        HashMap<String, String> map = readcsvdata();

        List<String> RBCDiameterlist = new ArrayList<String>();

        String Diameter = map.get("RBC_Diameter_mean_val") + " " + map.get("RBC_Diameter_mean_lb") + " - " + map.get("RBC_Diameter_mean_ub") + " µm " + map.get("RBC_Diameter_std_val") + " " + map.get("RBC_Diameter_std_lb") + " - " + map.get("RBC_Diameter_std_ub") + " µm";
        RBCDiameterlist.add(Diameter);
        return RBCDiameterlist;
    }

    //fetches RBC Pallor Ratio data from csv file
    public List<String> getRBCPallorRatioData() throws Exception{
        HashMap<String, String> map = readcsvdata();

        List<String> RBCPallorRatiolist = new ArrayList<String>();

        String PallorRatio = map.get("RBC_pallor_ratio_mean_val") + " " + map.get("RBC_pallor_ratio_mean_lb") + " - " + map.get("RBC_pallor_ratio_mean_ub") + " " + map.get("RBC_pallor_ratio_std_val") + " " + map.get("RBC_pallor_ratio_std_lb") + " - " + map.get("RBC_pallor_ratio_std_ub");
        RBCPallorRatiolist.add(PallorRatio);

        return RBCPallorRatiolist;
    }
}
