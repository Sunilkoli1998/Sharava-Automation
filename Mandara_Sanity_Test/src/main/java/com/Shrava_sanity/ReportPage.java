package com.Shrava_sanity;

import com.data.Shrava_data.PropertiesFile;
import com.data.MandaraData.ReadMandaraData;

import com.data.ImgDiffPercent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.*;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReportPage extends ListReport {

    public Actions actions;
    public Properties props;
    public WebDriver driver;
    public WebDriverWait wait;
    static String reviewerValue;
    public Login login;

    public ReadMandaraData readmandaradata;
    public ImgDiffPercent imagecomp;
    private final Logger logger = LogManager.getLogger(ReportPage.class);
    private static DecimalFormat df = new DecimalFormat("0.00");
    public String selectedrowdetails = "abc";
    String curDir = System.getProperty("user.dir");


    public ReportPage(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        Actions actions = new Actions(driver);

        props = PropertiesFile.prop;
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readShonitPropertiesFile();
        PropertiesFile.readShonitListReportFile();
        PropertiesFile.readSearchFilterFile();

        int time = Integer.parseInt(props.getProperty("timeout"));
        wait = new WebDriverWait(driver, time);
        readmandaradata = new ReadMandaraData();
        imagecomp = new ImgDiffPercent();
    }

//---------------------------  Verify Shonit Report Page -------------------------------------

    //
    public String openedsampledetails() {
        return this.selectedrowdetails;
    }

    public boolean clickOnSigupleIcon() throws InterruptedException {
        WebElement icon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("sigtupleicon"))));
        if (icon.isDisplayed()) {
            icon.click();
            WebElement homepage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(props.getProperty("apptoolbar"))));
            if (homepage.isDisplayed()) {
                Thread.sleep(2000);
                logger.info("Clicked on sigtuple icon and home page loaded ");
                return true;
            }
        }

        return false;
    }


    public boolean veerifyassignthereportafteropeninghtereport() throws InterruptedException {
        boolean flag= false;
        //Thread.sleep(3000);
        driver.findElement(By.xpath(props.getProperty("menuCard"))).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(props.getProperty("shonitCard"))).click();
        Thread.sleep(2000);
        List<WebElement> reportstatus = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("reportstatus"))));
        logger.info("Successfully came inside list");

        for(int s= 0; s<=reportstatus.size()-1; s++)

        {
            String status = reportstatus.get(s).getText();
            logger.info(status);
            if (status.equals("Report Generated"))
            {
                logger.info(status);
                reportstatus.get(s).click();
                logger.info("Succeasfully opened the report");
                Thread.sleep(6000);
                flag = true;
                break;
            }


        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("Addassignee")))).click();
       // Thread.sleep(3000);
        List<WebElement> reviewerNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(props.getProperty("reviwernames")))));
        //Thread.sleep(3000);
        for (int i=0;i<=reviewerNames.size()-1;i++)
        {
            String reviewerValue = reviewerNames.get(i).getText();
            logger.info(reviewerValue);
            if (reviewerValue.equals(props.getProperty("Reviewername")))
            {
                reviewerNames.get(i).click();
                flag=true;
                break;
            }
        }
      return  flag;
    }

    public boolean searchSampelID() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(1000);
        driver.findElement(By.xpath(props.getProperty("menuCard"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(props.getProperty("shonitCard"))).click();
        Thread.sleep(3000);
        WebElement searchicon1 = driver.findElement(By.xpath("//i[@class='material-icons hide-search-icon ng-star-inserted']"));
        // WebElement searchicon1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchicon"))));
        boolean status = false;
        if (searchicon1.isDisplayed()) {
            searchicon1.click();
            WebElement searchicon = driver.findElement(By.xpath("//div//input[contains(@class,'search-input')]"));
            searchicon.click();
            searchicon.clear();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            searchicon.sendKeys(props.getProperty("searchSampleID"));
            Thread.sleep(1000);
            searchicon.sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            String statusText = "//table[@role='grid']//tbody//tr[1]//td[4]";
            driver.findElement(By.xpath(statusText)).click();
            Thread.sleep(2000);
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("Addassignee")))).click();
        // Thread.sleep(3000);
        List<WebElement> reviewerNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(props.getProperty("reviwernames")))));
        //Thread.sleep(3000);
        for (int i=0;i<=reviewerNames.size()-1;i++)
        {
            String reviewerValue = reviewerNames.get(i).getText();
            logger.info(reviewerValue);
            if (reviewerValue.equals(props.getProperty("Reviewername")))
            {
                reviewerNames.get(i).click();
                status=true;
                break;
            }
        }

        return status;
    }

    public String reportPageHeader() {
        props = PropertiesFile.prop;
        String headerText = driver.findElement(By.xpath(props.getProperty("reportHeader"))).getText();
        System.out.println(headerText);
        return headerText;
    }

    public String verifyDisclaimerNote() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        String Disclaimer = driver.findElement(By.xpath(props.getProperty("Disclaimer"))).getText();
        System.out.println(Disclaimer);
        return Disclaimer;
    }






    public String pbsReportHeader() {
        props = PropertiesFile.prop;
        List<WebElement> pbsReportHeadings = driver.findElements(By.xpath("//*[@id='tablecontainer']/table/thead/tr/th"));
        String header = "";
        for (WebElement pbsReportHeading : pbsReportHeadings) {
            header = header + (pbsReportHeading.getText()) + ",";
        }
        System.out.println(header);
        return header;
    }

    public String getListOfReportName() {
        props = PropertiesFile.prop;
        List<WebElement> allReportName = driver.findElements(By.xpath(props.getProperty("allreportname")));
        String reportsName = "";
        for (WebElement allReportNames : allReportName) {
            reportsName = reportsName + (allReportNames.getText()) + ",";
        }
        System.out.println(reportsName);
        return reportsName;
    }

    //Verify the presence of Update approve reject button
    public String reportAccessibilityButton() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        //driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        List<WebElement> allButtons = driver.findElements(By.xpath(props.getProperty("accessibilityButton")));
        String allConatiners = "";
        for (WebElement allButton : allButtons) {
            allConatiners = allConatiners + (allButton.getText()) + ",";
        }
        System.out.println(allConatiners);
        return allConatiners;
    }


    public int additionOfAllValue() {
        props = PropertiesFile.prop;
        // List<WebElement> elements=driver.findElements(By.tagName("tr"));
        int sum = 0;
        double DoubleValue;
        List<WebElement> elements = driver.findElements(By.tagName("tr"));
        for (int i = 1; i <= elements.size() - 1; i++) {
            WebElement allValue = (WebElement) driver.findElements(By.xpath("//section[@id='otherMetrics']//table//tbody//tr[" + i + "]//td[2]"));
            String valueCount = allValue.getText();
            DoubleValue = Double.parseDouble(valueCount);
            sum = (int) (sum + DoubleValue);

        }
        System.out.println("Total Sum : " + sum);

        return sum;
    }










    public String reassignmentdetails() {
        props = PropertiesFile.prop;
        String reassignment = driver.findElement(By.xpath(props.getProperty("reassignment"))).getText() + "";
        System.out.println(reassignment);
        return reassignment;
    }

    public boolean reassingedWithDifferentUsers() throws InterruptedException {
        props = PropertiesFile.prop;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("addAssignee")))).click();
        Thread.sleep(1000);
        List<WebElement> reviewerNames = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        for (WebElement reviewerName : reviewerNames) {
            reviewerValue = reviewerName.getText().trim();
            if (reviewerValue.startsWith(props.getProperty("username3"))) {
                reviewerName.click();
                break;
            }
        }
        WebElement comment = driver.findElement(By.xpath(props.getProperty("comment")));
        comment.click();
        comment.clear();
        comment.sendKeys(props.getProperty("comments"));
        comment.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("reassign")))).click();
        Thread.sleep(3000);
        // driver.findElement(By.xpath(props.getProperty("saveChangeButton"))).click();

        return true;
    }


    public boolean downloadsCSVReport() {
        props = PropertiesFile.prop;
        driver.findElement(By.xpath(props.getProperty("download"))).click();
        Actions actions1 = new Actions(ReportPage.this.driver);
        actions1.moveToElement(driver.findElement(By.xpath(props.getProperty("CSVReport")))).click().build().perform();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return true;
    }

    public String downloadReport() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(2000);
        WebElement download = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("download"))));
        download.click();
        String reportFormat = driver.findElement(By.xpath(props.getProperty("downloadContent"))).getText();
        Actions actions1 = new Actions(ReportPage.this.driver);
        actions1.moveToElement(download).click().build().perform();
        return reportFormat;
    }


    public String originalReport() {
        props = PropertiesFile.prop;
        String original = driver.findElement(By.xpath(props.getProperty("originalReport"))).getText();
        System.out.println(original);
        // driver.findElement(By.xpath("//span[@class='mat-option-text'][normalize-space()='Modified']")).click();
        return original;
    }


    public String auditLog() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(1000);
        String auditLog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("auditLog")))).getText();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("auditLog")))).click();
        Thread.sleep(3800);
        String productVersion = driver.findElement(By.xpath(props.getProperty("auditLogsHeader"))).getText();
        System.out.println(productVersion);
        Thread.sleep(1000);
        driver.findElement(By.xpath(props.getProperty("backToReport"))).click();
        //Thread.sleep(1000);
        ////i[normalize-space()='keyboard_backspace']
        return auditLog;
    }

    public String verifySlideID() {
        props = PropertiesFile.prop;
        String SlideID = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(props.getProperty("sample_id")))).getText();
        return SlideID;
    }

    public String verifyslidedetails() throws InterruptedException {
        Thread.sleep(1000);
        String detail = "";
   driver.findElement(By.xpath("//div/div/div[3]/mat-accordion/mat-expansion-panel/mat-expansion-panel-header")).click();
   Thread.sleep(1000);
        List<WebElement> sampledetails = driver.findElements(By.xpath(props.getProperty("SampleDetails")));
           for (int i=0;i<=sampledetails.size()-1;i++)
           {
                detail=detail+sampledetails.get(i).getText();
                logger.info(detail);

           }

        return detail;

    }


    //change version from modified to original
    public Boolean changeVersion() throws Exception {
        props = PropertiesFile.prop;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("version")))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("versionoriginal")))).click();
        Thread.sleep(1000);
        String version = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("version")))).getText();
        return version.contains("Original");
    }


    public boolean verifyReportPage() throws InterruptedException {
        props = PropertiesFile.prop;
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(props.getProperty("cardonReportpage")))).isDisplayed();
    }



    //


    // get Desclaimer
    public String getDesclaimer() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("desclaimer")))).getText();
    }

    //fetch status container labels
    public String getStatusContainers() {
        props = PropertiesFile.prop;
        String labels = "";
        List<WebElement> sampledetailsheader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(props.getProperty("statuscontainer"))));

        for (WebElement sampledetailarray : sampledetailsheader)
            labels = labels + sampledetailarray.getText();
        System.out.println(labels);
        return labels;
    }

    //fetch status container labels
    public String getStatusContainersColors() {
        props = PropertiesFile.prop;
        String colors = "";
        List<WebElement> sampledetailsheader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(props.getProperty("statuscontainer") + " i")));

        for (WebElement sampledetailarray : sampledetailsheader)
            colors = colors + sampledetailarray.getAttribute("class").trim() + " ";
        System.out.println(colors);
        return colors;
    }

    //visibility of export button
    public boolean visibilityOfExportButton() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement export = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(props.getProperty("export"))));
        if (export.isEnabled()) {
            logger.info("export button is visible at top of the report page");
            return true;
        }
        logger.info("export button is not visible or not clickable at top of the page");
        return false;
    }

    //visibility of export button
    public String verifyOptionsInExportButton() throws InterruptedException {
        props = PropertiesFile.prop;
        String options = "";
        WebElement export = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(props.getProperty("export"))));
        if (export.isEnabled()) {
            export.click();
            Thread.sleep(2000);
            options = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(props.getProperty("listofexportoptions")))).getText();
            Actions action = new Actions(driver);
            action.moveToElement(export).click().build().perform();
        }
        //logger.info("list of options : "+options);
        return options;
    }





// ----------------------------------- Globle Methods ------------------------




    public boolean clickOnFirstSample() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(2000);
        driver.findElement(By.xpath("//table[@role='grid']//tbody//tr[1]//td[4]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath(props.getProperty("addAssignee"))).click();
        Thread.sleep(1000);
        List<WebElement> reviewerNames = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        for (WebElement reviewerName : reviewerNames) {
            reviewerValue = reviewerName.getText().trim();
            if (reviewerValue.startsWith(props.getProperty("username3"))) {
                reviewerName.click();
                break;
            }
        }
        return true;
    }












    public String clickOnExpandButton() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement element = driver.findElement(By.xpath(props.getProperty("firstPatchImage")));
        actions.moveToElement(element).perform();
        driver.findElement(By.xpath(props.getProperty("clickOnViewFullImage"))).click();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement canvasVisibility = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ImageVisibility"))));
        canvasVisibility.isDisplayed();
        Thread.sleep(2000);
        actions.moveToElement(canvasVisibility).build().perform();
        String text = driver.findElement(By.xpath(props.getProperty("extractedCellText"))).getText();
        driver.findElement(By.xpath(props.getProperty("clickOnClose"))).click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        return text;
    }





    public boolean verifyDefauldLandingPage() throws InterruptedException {
        WebElement tabs = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("currentselectedtab"))));
        if (tabs.isDisplayed() && tabs.getText().contains("Summary"))
            return true;
        return false;
    }


    //verify list of patches and it size
    public boolean findpatchrelatedresult(int count) throws InterruptedException {
        props = PropertiesFile.prop;
        int patchsize = 0;
        int refsize = 0;
        int totalpatches = 0;
        int patchinfirst = 0;
        Thread.sleep(2000);
        //String patchheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("patchheader")))).getText();
        try {
            for (int i = 0; i <= 5; i++) {
                patchsize = driver.findElements(By.cssSelector(props.getProperty("listofpatches"))).size();
                if (patchsize == 12)
                    break;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
        try {
            for (int i = 0; i < 5; i++) {
                refsize = driver.findElements(By.cssSelector(props.getProperty("refimages"))).size();
                if (refsize == 4)
                    break;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
        try {
            String temp_data = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("totalpatch")))).getText();
            String[] arrOfStr = temp_data.split("of", 2);
            totalpatches = Integer.valueOf(arrOfStr[1].trim());
            patchinfirst = Integer.valueOf(arrOfStr[0].split("-", 2)[1].trim());
        } catch (Exception e) {
        }
        System.out.println(totalpatches + " : " + String.valueOf(patchsize) + " : " + String.valueOf(refsize));
        if (patchinfirst == patchsize && (totalpatches == count || totalpatches == 100) && refsize == 4)
            return true;
        return false;
    }


    public boolean verifypatches(String tablepath) throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        boolean status = false;
        List<WebElement> tablerows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(tablepath)));
        List<WebElement> rowdata;
        WebElement firstpatch = null;
        boolean fullfov = true;
        for (int i = 1; i <= tablerows.size(); i++) {
            try {
                rowdata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(tablepath + "[" + String.valueOf(i) + "]/td")));
                System.out.println(rowdata);
                String key = rowdata.get(0).getText().trim();
                System.out.println("key value is " + key);
                String image = rowdata.get(rowdata.size() - 1).getText().trim();
                System.out.println("The image string contains " + image);
                if (image.contains("collections")) {
                    firstpatch = rowdata.get(rowdata.size() - 1);
                    System.out.println("1st patch x path " + firstpatch);
                    firstpatch.click();
                    String patchheader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(props.getProperty("patchheader")))).getText();
                    if (patchheader.equalsIgnoreCase(key) || patchheader.toLowerCase().contains(key.substring(0, 2).toLowerCase())) {
                        if (patchheader.contains("collection"))
                            System.out.println(key + " : " + patchheader.split(" ")[1]);
                        else
                            System.out.println(key + " : " + patchheader);
                    } else {
                        logger.info(key + " : for this row table name and patch header is not matching");
                        status = status && false;
                    }
                    Thread.sleep(2000);
                    status = this.findpatchrelatedresult(Integer.valueOf(rowdata.get(1).getText().trim()));
                    if (fullfov) {
                        if (this.checkfullfov()) {
                            logger.info(key + " : show image in full FOV is verified");
                            fullfov = false;
                        } else
                            logger.info(key + " : failed to show image in full FOV");
                    }
                    if (!status && !key.contains("Unclassified"))
                        break;
                }
            } catch (Exception e) {
            }
        }
        firstpatch.click();
        return status && !fullfov;
    }

    public boolean checkfullfov() throws InterruptedException {
        List<WebElement> listofpatches = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listofpatches"))));
        if (listofpatches.size() == 0)
            return false;
        boolean status = false;
        Actions action = new Actions(driver);
        action.moveToElement(listofpatches.get(0)).build().perform();
        Thread.sleep(2000);
        WebElement imgloc = driver.findElement(By.cssSelector(props.getProperty("getfirstpatch")));
        action.moveToElement(imgloc).build().perform();
        WebElement patchDispSrcOpen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("Displaypatch"))));
        action.moveToElement(patchDispSrcOpen).click().build().perform();
        //System.out.println("clicked on first patch");
        try {
            WebElement displycanvas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("displycanvas"))));
            if (displycanvas.isDisplayed()) {
                Thread.sleep(2000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("dispsrcclose")))).click();
                status = true;
            }
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("dispsrcclose")))).click();
            status = false;
        }
        return status;
    }

    //verify grading in diff tables
    public boolean verifyGrading(Map<String, Map<String, String>> tabledata, String filename) throws Exception {
        props = PropertiesFile.prop;
        Map<String, String> ShonitRefData = readmandaradata.ReadCSVFile(filename);
        System.out.println(ShonitRefData);

        boolean status = true;
        Set setofKeys = tabledata.keySet();
        Iterator itr = setofKeys.iterator();
        while (itr.hasNext()) {
            String key = (String) itr.next();
            if (key.equalsIgnoreCase("TOTAL"))
                continue;
            Map<String, String> value = tabledata.get(key);
            if (!value.keySet().contains("Grading")) {
                logger.info("Grading colums is not availabe for this table ");
                return false;
            }
            if (value.get("Percentage").length() == 0) {
                logger.info(key + " : this row is not having any percentage value");
                continue;
            }
            float percentage = Float.valueOf(value.get("Percentage").split("%")[0].trim());
            String grading = value.get("Grading").trim();
            if (grading.length() == 0) {
                logger.info(key + " : this row is not having grading ");
                status = status && false;
                continue;
            }
            String ref = ShonitRefData.get(key);
            float startvalue = Float.valueOf(ref.split("-")[0].trim());
            float endvalue = Float.valueOf(ref.split("-")[1].trim());
            System.out.println(String.valueOf(startvalue) + " : " + String.valueOf(endvalue) + " : " + String.valueOf(percentage) + " : " + grading);
            if ((percentage < startvalue && grading.equalsIgnoreCase("+")) ||
                    (percentage > endvalue && grading.equalsIgnoreCase("+++")) ||
                    (percentage >= startvalue && percentage <= endvalue && grading.equalsIgnoreCase("++"))) {
                logger.info(key + " : is passed in grading validation");
            } else {
                status = status && false;
                logger.info(key + " : is failed in grading validation");
            }

        }
        return status;
    }


    //Get list of rows in External metrics
    public Map<String, Map<String, String>> collectTableData(String rowpath, String headerpath) throws InterruptedException {
        props = PropertiesFile.prop;
        Map<String, Map<String, String>> tabledata = new HashMap<String, Map<String, String>>();
        List<WebElement> tablerows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(rowpath)));
        List<WebElement> tablecol = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(headerpath)));

        Map<Integer, String> head_map = new HashMap<Integer, String>();
        for (int i = 0; i < tablecol.size(); i++) {
            System.out.println(tablecol.get(i).getText().trim());
            head_map.put(i, tablecol.get(i).getText().trim());
        }

        for (int i = 1; i <= tablerows.size(); i++) {
            try {
                List<WebElement> rowdata = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath(rowpath + "[" + String.valueOf(i) + "]/td")));
                String key = rowdata.get(0).getText().trim();
//        		if(key.toLowerCase().contains("total")) {
//        			status=true;
//        			break;
//        		}
                Map<String, String> temprowmap = new HashMap<String, String>();
                for (int j = 1; j < rowdata.size(); j++)
                    temprowmap.put(head_map.get(j), rowdata.get(j).getText().trim());
                tabledata.put(key, temprowmap);

            } catch (Exception e) {
            }
        }

        //---printing map data
        Set setofKeys = tabledata.keySet();
        Iterator itr = setofKeys.iterator();
        while (itr.hasNext()) {
            String key = (String) itr.next();
            Map<String, String> value = tabledata.get(key);
            Set setofKeys1 = value.keySet();
            Iterator itr1 = setofKeys1.iterator();
            System.out.println("key : " + key);
            while (itr1.hasNext()) {
                String key1 = (String) itr1.next();
                System.out.println("\t" + key1 + " : " + value.get(key1));
            }
            System.out.println();
        }

        return tabledata;
    }


    public void getLoadedSampleData(String product) throws Exception {
        props = PropertiesFile.prop;
        String sample_id = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("sample_id")))).getText().trim();
        String sample_des = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("sample_des")))).getText().trim();
        String date = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("sample_date")))).getText().trim();
        date = date.replace(" ", "#");
        String workingdir = System.getProperty("user.dir");
        String dataoutput = workingdir + "/PythonFiles/" + product;

        String env = props.getProperty("Environment");
        String command = "python3 " + workingdir + "/PythonFiles/" + product + "/get_sample_data.py --Sample \'" + sample_id + "\' --Des \'" + sample_des + "\' --Date " + date + " --Output " + dataoutput + " --Env " + env;

        try {
            FileWriter f = new FileWriter(workingdir + "/PythonFiles/" + product + "/sample_details.txt");
            BufferedWriter b = new BufferedWriter(f);
            PrintWriter p = new PrintWriter(b);
            p.print(command);
            p.flush();
            p.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

        try {
            FileWriter f = new FileWriter(workingdir + "/PythonFiles/" + product + "/capturedFOV.txt");
            BufferedWriter b = new BufferedWriter(f);
            PrintWriter p = new PrintWriter(b);
            String capturedimages = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("capturedimages")))).getText().trim();
            p.print(capturedimages);
            p.flush();
            p.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }



    public boolean createCSVFortable(String product, Map<String, Map<String, String>> tabledata, String tablename, ArrayList<String> columns) throws Exception {
        /*
         * if you are going to start with new product , please create folder structure as follows :
         * PythonFiles/"+product+"/MandaraFile
         * PythonFiles/"+product+"/DBFile
         * PythonFiles/"+product+"/dataTemplet
         */
        Map<String, String> csvdata = new HashMap<String, String>();
        try {
            Set setofKeys = tabledata.keySet();
            Iterator itr = setofKeys.iterator();

            String workingdir = System.getProperty("user.dir");
            String dataoutput = workingdir + "/PythonFiles/" + product + "/MandaraFile";
            FileWriter csvWriter = new FileWriter(dataoutput + "/" + tablename + ".csv");
            csvWriter.append("Mandara");
            csvWriter.append(",");
            csvWriter.append("Mandara-Value");
            csvWriter.append("\n");

            while (itr.hasNext()) {
                String key = (String) itr.next();
                Map<String, String> value = tabledata.get(key);
                for (String col : columns) {

                    String inputkey = key + "-" + col;
                    String inputvalue = "";
                    if (col.contentEquals("Percentage"))
                        inputvalue = value.get(col).split("%")[0].trim();
                    else
                        inputvalue = value.get(col);
                    System.out.println(inputkey + " : " + inputvalue);
                    csvdata.put(inputkey, inputvalue);
                    csvWriter.append(inputkey + "," + inputvalue);
                    csvWriter.append("\n");
                }
            }
            csvWriter.flush();
            csvWriter.close();
            try {
                this.getLoadedSampleData(product);
            } catch (Exception e) {
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    // this method is use for display the title
    public String waitForTitleDisplay(String title) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.titleIs(title));
        return title;
    }




















    private List<WebElement> getAllRows(WebElement table) {
        return table.findElements(By.cssSelector("table tr"));
    }

    public String verifyRowItems(WebElement table, List<String> actualValue) {
        List<WebElement> allRows = getAllRows(table);
        for (WebElement allRow : allRows) {
            for (String eachValue : actualValue) {
                if (allRow.getText().equalsIgnoreCase(eachValue)) {
                    return eachValue;
                }
            }
        }
        return null;
    }


    public String verifyDetectedAndNonDetectedNote()
    {
        String Note="";
        List<WebElement> notes = driver.findElements(By.xpath(props.getProperty("note")));
        for (WebElement note:notes) {
            Note=Note+note.getText();
        }
        return Note;
    }

    public String verifyTheNorAbReferenceNote()
    {
        String message="";
        List<WebElement> flages=driver.findElements(By.xpath(props.getProperty("flage")));
        for (WebElement flage:flages) {
            message=message+flage.getText();

        }
        return message;
    }

    public String verifyPresenceOfApproveButton()
    {
      String buttons=driver.findElement(By.xpath(props.getProperty("Approvebuttons"))).getText();
        return buttons;
    }
    public String verifyPresenceOfRejectButton()
    {
        String buttons=driver.findElement(By.xpath(props.getProperty("Rejectbuttons"))).getText();
        return buttons;
    }

    public String verifyPresenceOfEditButton()
    {
        String buttons=driver.findElement(By.xpath(props.getProperty("EditButton"))).getText();
        return buttons;
    }




  //verifying the edit option for detected and not detected results.
  public boolean verifytheSelectionofDandNDResults() throws InterruptedException {
        boolean flag = false;
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("EditButton")))).click();


      for(int j=1;j<=1;j++)
     {
     for(int i=0;i<=3;i++)
     {
        List<WebElement> reultssss=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("DandNDreults"))));
        String text = reultssss.get(i).getText();
        logger.info("The original result is"+" "+text);

        List<WebElement> dropdown=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("dropdonwsss"))));
        dropdown.get(i).click();
        Thread.sleep(2000);

        List<WebElement> DandNDlist=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("DandNDlist"))));
        for(int k= 0;k<=DandNDlist.size()-1;k++)
        {
            String text1=DandNDlist.get(k).getText();

            if(text1.equals(text))
            {
                logger.info("Both the text are same ");
            }
            else
            {
                DandNDlist.get(k).click();
                break;
            }

        }

        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("updatebutton111")))).click();
        Thread.sleep(2000);
        WebElement changesheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2")));
        if (changesheader.getText().equals("Please confirm that you want to make the following changes to the Urine Microscopy Report.")) {
            logger.info("The header is matching while updating the results");
            flag = true;
        }
        Thread.sleep(2000);
        String updatedval = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("updatedresultvalue")))).getText();
        if (updatedval.startsWith(text)) {
            logger.info("updated value is reflecting while updating the results");
            flag = true;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Confirm')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-icon[contains(text(),'close')]"))).click();
        Thread.sleep(6000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("EditButton")))).click();

    }
}
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("cancelButton")))).click();


return flag;

  }



  public boolean verifythecountsandGradeofResults(){
        boolean flag =false;
      String[] arr = {"//table[1]/tbody[1]/tr[1]/td[2]/span[1]", "//table[1]/tbody[1]/tr[2]/td[2]/span[1]","//table[1]/tbody[1]/tr[3]/td[2]/span[1]"};
      String[] arr1={"//table[1]/tbody[1]/tr[1]/td[4]/span[1]","//table[1]/tbody[1]/tr[2]/td[4]/span[1]","//table[1]/tbody[1]/tr[3]/td[4]/span[1]"};
      for(int s=0;s<=arr.length-1;s++)
      {
          WebElement result= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(arr[s])));
          String Resultvalue= result.getText();
         int Resultvalue1= Integer.parseInt(Resultvalue);
          WebElement grade= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(arr1[s])));
          String gradevalue= grade.getText();
          logger.info(gradevalue);
          logger.info(Resultvalue1);
          logger.info("Came inside for loop");
          if(Resultvalue1<=5&&gradevalue.equals("NS"))
          {
              logger.info("The grade value is"+" "+gradevalue);
              flag =true;
          }
          else if (Resultvalue1>5 && Resultvalue1<=10 && gradevalue.equals("1+")){
              logger.info("The grade value is"+" "+gradevalue);
              flag =true;
          }

          else if (Resultvalue1>10 && Resultvalue1<=20 && gradevalue.equals("2+")){
              logger.info("The grade value is"+" "+gradevalue);
              flag =true;
          }

          else if (Resultvalue1>20 && Resultvalue1<=50 && gradevalue.equals("3+")){
              logger.info("The grade value is"+" "+gradevalue);
              flag =true;
          }
          else if (Resultvalue1>50  && gradevalue.equals("4+")){
              logger.info("The grade value is"+" "+gradevalue);
              flag =true;
          }

          else
          {
              flag=false;
          }

      }
      return flag;

  }

    public String Expanding1StPatch() throws InterruptedException {
Thread.sleep(3000);
               WebElement hoverable = driver.findElement(By.xpath(props.getProperty("1stPatch")));
               new Actions(driver)
                       .moveToElement(hoverable)
                       .perform();
               driver.findElement(By.xpath(props.getProperty("expandButton"))).click();
               Thread.sleep(4000);
             String  extractedCell = driver.findElement(By.xpath(props.getProperty("extractedCell"))).getText();
             logger.info(extractedCell);
               Thread.sleep(3000);

     return extractedCell;

    }

    public String verifyListOfcellNmaesInfullpatchView() throws InterruptedException {
        Thread.sleep(3000);
        String cellName="";
        List<WebElement> cells=driver.findElements(By.xpath(props.getProperty("cells")));
        for (WebElement cell:cells) {
            cellName=cellName+cell.getText();
        }
        logger.info(cellName);
        return cellName;
    }


    public void verifyPresenceAndFunctionalalityOfUpandDownArrow() throws InterruptedException {
        WebElement downarrow=driver.findElement(By.xpath(props.getProperty("downArrow")));
        downarrow.click();
        Thread.sleep(2000);
        WebElement upArrow=driver.findElement(By.xpath(props.getProperty("upArrow")));
        upArrow.click();
        Thread.sleep(2000);

    }

    public String vrifyFuctionalityOfZoomInbutton() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath(props.getProperty("ZoomIn"))).click();
        Thread.sleep(4000);
        String zoomedLevel=driver.findElement(By.xpath(props.getProperty("ZoomLevel"))).getText();
        return zoomedLevel;
    }

    public String vrifyFuctionalityOfZoomOutbutton() throws InterruptedException {
        driver.findElement(By.xpath(props.getProperty("ZoomOut"))).click();
        Thread.sleep(4000);
        String zoomedLevel=driver.findElement(By.xpath(props.getProperty("ZoomLevel"))).getText();
        return zoomedLevel;
    }

    public String verifyResetButtonFuctionality() throws Exception {

        driver.findElement(By.xpath(props.getProperty("ZoomIn"))).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(props.getProperty("reset1"))).click();
        Thread.sleep(2000);
        String zoomedLevel=driver.findElement(By.xpath(props.getProperty("ZoomLevel"))).getText();
        return zoomedLevel;


    }

    public String verifyOptionPresentIntheDropDown() throws InterruptedException {
        String OptionsName="";
        driver.findElement(By.xpath(props.getProperty("DropDown"))).click();
        Thread.sleep(2000);
        List<WebElement> options = driver.findElements(By.xpath(props.getProperty("zoomOptionIndropDown")));
        for (int i=0;i<=options.size()-1;i++) {
            logger.info("came inside the loop");
            OptionsName=OptionsName+options.get(i).getText();
            logger.info(OptionsName);
        }
        return OptionsName;
    }

  public String SelectionOf100xoption() throws InterruptedException {
      driver.findElement(By.xpath(props.getProperty("DropDown"))).click();
      Thread.sleep(2000);
      driver.findElement(By.xpath(props.getProperty("100xOption"))).click();
      Thread.sleep(2000);
      String zoomedLevel=driver.findElement(By.xpath(props.getProperty("ZoomLevel"))).getText();
      return zoomedLevel;
  }
  public String selectionOf40xOption() throws InterruptedException {
      Thread.sleep(2000);
      driver.findElement(By.xpath(props.getProperty("DropDown"))).click();
      Thread.sleep(2000);
      driver.findElement(By.xpath(props.getProperty("40xoption"))).click();
      Thread.sleep(2000);
      String zoomedLevel=driver.findElement(By.xpath(props.getProperty("ZoomLevel"))).getText();
      return zoomedLevel;
  }
  public String verifyRollOverImageNote()
  {
      String RolloverNote=driver.findElement(By.xpath(props.getProperty("rollOverImagenote"))).getText();
      return RolloverNote;
  }

    public String verifyDefaultCheckBoxselection()
    {
        String checkBox=driver.findElement(By.xpath(props.getProperty("rolloverCheckBox"))).getAttribute("aria-checked");
        return checkBox;
    }
    public String closingFullpatchview() throws InterruptedException {
        driver.findElement(By.xpath(props.getProperty("closeButton"))).click();
        Thread.sleep(4000);
        String Header=driver.findElement(By.xpath(props.getProperty("header1"))).getText();
        return Header;
    }
}




