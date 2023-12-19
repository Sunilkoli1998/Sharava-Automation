package com.Shrava_sanity;

import com.data.Shrava_data.PropertiesFile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ListReport extends MandaraHome {


    public Properties props;
    public WebDriver driver;
    public WebDriverWait wait;
    static String reviewerValue;
    String searchdata = "S";


    public ListReport(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        PropertiesFile.readShonitListReportFile();
        PropertiesFile.readPropertiesFIle();
        PropertiesFile.readMandaraHomePropertiesFile();
        // PropertiesFile.readShonitPropertiesFile();

        props = PropertiesFile.prop;
        int time = 30;
        wait = new WebDriverWait(driver, time);

    }

    //Click on ShravaListReport
    public boolean clickonListReport(String product) throws InterruptedException {
        props = PropertiesFile.prop;
        // boolean flag = false;
        //String listreportlink=props.getProperty("productlistreport1");
        WebElement listreport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("productlistreport1"))));

       /*if (listreport.getText().contains("View List Reports"))
        {*/
        if (listreport.isDisplayed()) {
            listreport.click();
            return true;
        }
        return false;
    }

    //List Report page verification
    public boolean verifyVersionCard() {
        props = PropertiesFile.prop;
        try {
            //Thread.sleep(5000);
            WebElement card = driver.findElement(By.cssSelector(props.getProperty("drishtiaboutappbox")));
            if (card != null && card.isDisplayed())
                return true;
        } catch (Exception e) {
        }
        return false;
    }

    //List Report page verification
    public String verifyPlateformVersion() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("plateformversion")))).getText();
    }

    public String verifyHelpButton() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("helpicon")))).getText();

    }

    public boolean verifyNotificationIcon(String product) throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement notificationIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("notificationicon"))));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //notificationIcon.click();
        //Actions action=new Actions(driver);
        // Thread.sleep(1000);
        // action.moveToElement(notificationIcon).click().build().perform();

        return true;
    }

    //List Report page verification
    public String verifyProductName() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("productname")))).getText();
    }

    //List Report page verification
    public String verifySoftwareVersion() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("softwareversion")))).getText();
    }

    // close about product card
    public boolean closeproductVersionCard() throws InterruptedException {
        props = PropertiesFile.prop;
        // Thread.sleep(2000);
        WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("closeappabout"))));
        if (close.isDisplayed()) {
            //Thread.sleep(2000);
            close.click();
            return true;
        }
        return false;
    }

    //List Report page verification
    public String verifyListReportPage() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("checkReportPage")))).getText();
    }

    public String checkmodulname() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("modulname")))).getText();
    }

    //check  notepad icon is available or not
    public Boolean checkNotepadIcon() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("notepadIcon")))).isDisplayed();
    }

    //Check if Search field is displayed
    public Boolean searchfield() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchicon")))).isDisplayed();
    }

    //Checks if filter icon is displayed
    public Boolean filtericon() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filtericon")))).isDisplayed();
    }

    public Boolean refreshicon() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("refreshicon")))).isDisplayed();
    }

    //get Disclaimer information
    public String getDisclaimer() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("disclaimerrow")))).getText();
    }

    //get Copy right information
    public String getCopyrightinfo() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("copyrightinfo")))).getText();
    }

    //get TOS values
    public String getTOSinfo() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("tosinfo")))).getText();
    }

    //Check visibility of checkpaginationeditbox in the list report
    public Boolean checkpaginationeditbox() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean status = false;
        WebElement currentpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enterpagenumber"))));
        if (currentpage.isDisplayed()) {
            try {
                currentpage.click();
                //Thread.sleep(2000);
                status = true;
            } catch (Exception e) {
            }
        }
        return status;
    }

    public Boolean checkpaginationposition() {
        props = PropertiesFile.prop;
        int current_page = 1;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("pagination_pos")))).isDisplayed();
    }

    //Check visibility of checkpaginationchevronicons in the list report
    public Boolean checkpaginationchevronicons() {
        props = PropertiesFile.prop;
        int current_page = 1;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("chevronicon")))).isDisplayed();
    }

    //this will fetch report tableheader list
    public String getListReportTableheader() {
        props = PropertiesFile.prop;
        List<WebElement> tableheader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("tableheader"))));
        String header = "";
        for (WebElement tableheaderString : tableheader) {
            header = header + (tableheaderString.getText()) + ",";
        }
        System.out.println(header);
        return header;
    }

    //Success, Failure, In Progress (SFI) icons
  /*  public String getStatusIcons()
    {
        props = PropertiesFile.prop;
        String table = props.getProperty("tablelocation");
        String failure_resion = props.getProperty("failure_resion");
        String status_text = props.getProperty("status_text");
        String status_icon=props.getProperty("status_icon");
        String all_status = props.getProperty("successicon"); 
        String unsuccessicon=props.getProperty("unsuccessicon"); 
        String status="true";
        
    	List<WebElement> tableheaders=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("tableheader"))));
    	int k=tableheaders.size();
    	
        List<WebElement> tablerows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("tablerows"))));

        for(int i=1;i<=tablerows.size();i++)
        {
        	String temp_path = table+String.valueOf(i)+"]/td["+String.valueOf(k)+status_text;

        	String statustext= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(temp_path))).getText();
        	temp_path = table+String.valueOf(i)+"]/td["+String.valueOf(k)+status_icon;
        	String statusicon= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(temp_path))).getText();
        	String fullstatus=statustext+"-"+statusicon;
        	if(all_status.contains(fullstatus) || unsuccessicon.contains(fullstatus));
        	else{
        		status=fullstatus;
        		return status;
        	}
        		
        }
        return status;
    }*/

    //find list of sample_id from first page
    public ArrayList<String> findlistofsampleID(String col) {
        props = PropertiesFile.prop;
        List<WebElement> tablerows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("tablerows"))));
        String sample_id = "";
        ArrayList<String> rows = new ArrayList<String>();
        for (int i = 1; i <= tablerows.size(); i++) {
            String temp_path = props.getProperty("tablerows") + "[" + String.valueOf(i) + col;
            String sampletext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(temp_path))).getText();
            rows.add(sampletext);
            System.out.println(sampletext);
        }
        return rows;
    }

    //validation of date sorting data
    public Boolean validate_sorted_row(String col) {
        props = PropertiesFile.prop;
        ArrayList<String> list = findlistofsampleID(col);
        List copy1 = new ArrayList(list);
        List copy2 = new ArrayList(list);
        boolean status = false;
        Collections.sort(copy1);
        Collections.sort(copy2);
        Collections.reverse(copy2);
        if (list.equals(copy1) || list.equals(copy2))
            status = true;

        //if(list.stream().sorted().collect(Collectors.toList()).equals(list) || list.stream().sorted().collect(Collectors.toList()).equals(Collections.reverse(list))) 

        return status;
    }

    //validation of date sorting data
    public int findcolumnNumber(WebElement ele) {
        props = PropertiesFile.prop;
        String label = ele.getText().trim();
        List<WebElement> tableheaders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("tableheader"))));
        for (int i = 0; i < tableheaders.size(); i++) {
            if (tableheaders.get(i).getText().contains(label))
                return i + 1;
        }
        return -1;
    }

    //verify case id sorting
    public Boolean verifyColumnSorting(String path) throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty(path))));
        if (button.isDisplayed())
            button.click();
        Thread.sleep(3000);
        String col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";
        return validate_sorted_row(col);
    }

    //verify Submit At sorting
    public Boolean verifySubmitAtSorting() throws Exception {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("datesort"))));
        if (button.isDisplayed())
            button.click();
        Thread.sleep(3000);
        String col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";
        ArrayList<String> datalist = findlistofsampleID(col);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        List<Date> datelist = new ArrayList<Date>();
        for (int i = 0; i < datalist.size(); i++) {
            //System.out.println(datalist.get(i));
            Date temp_date = formatter.parse(datalist.get(i));
            datelist.add(temp_date);
        }
        List copy1 = new ArrayList(datelist);
        Collections.sort(copy1, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        List copy2 = new ArrayList(copy1);
        Collections.reverse(copy2);
        boolean status = false;
        if (datelist.equals(copy1) || datelist.equals(copy2))
            status = true;
        System.out.println(datalist.size());

        return status;
    }


    //Verify the Search enter box
    public boolean searchSampelID(String product) throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement searchicon1 = driver.findElement(By.xpath("//i[@class='material-icons hide-search-icon ng-star-inserted']"));
        // WebElement searchicon1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchicon"))));
        boolean status = false;
        if (searchicon1.isDisplayed()) {
            searchicon1.click();
            WebElement searchicon =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[contains(@class,'search-input')]")));
            searchicon.click();
            searchicon.clear();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            searchicon.sendKeys(props.getProperty("searchSampleID"));
            Thread.sleep(1000);
            searchicon.sendKeys(Keys.ENTER);
            Thread.sleep(3000);
            // driver.findElement(By.xpath("//mat-icon[@class='close-icon mat-icon notranslate material-icons mat-icon-no-color']")).click();
            // Thread.sleep(1000);
            status = true;
        }
        return status;
    }

    public boolean addAssigneeOnListReport() throws InterruptedException {
        props = PropertiesFile.prop;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("AddAssignee")))).click();
        Thread.sleep(1000);
        List<WebElement> reviewerNames = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        Thread.sleep(1000);
        for (WebElement reviewerName : reviewerNames) {
            reviewerValue = reviewerName.getText().trim();
            if (reviewerValue.startsWith(props.getProperty("username3"))) {
                reviewerName.click();
                break;
            }
        }
        // WebElement comment = driver.findElement(By.xpath("//div[@id='main-div']//div/textarea"));
        //                comment.click();
        //                comment.clear();
        //                comment.sendKeys(props.getProperty("comments"));
        //                comment.sendKeys(Keys.ENTER);
        //                driver.findElement(By.xpath(props.getProperty("reassign"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-icon[@class='close-icon mat-icon notranslate material-icons mat-icon-no-color']"))).click();
        return true;
    }






   /* public boolean searchSampelID(String product) throws InterruptedException{
        props = PropertiesFile.prop;
        WebElement searchicon1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("searchicon"))));
        if(searchicon1.isDisplayed()) {
            searchicon1.click();}
            WebElement searchicon = driver.findElement(By.xpath(props.getProperty("searchfield")));
            searchicon.click();
            // searchicon.clear();
        if(product.contains("Shonit") || product.contains("Shrava")){
            this.searchdata=props.getProperty("searchSampleID");
            this.searchdata="";
            searchicon.sendKeys(this.searchdata);
            Thread.sleep(1000);
            searchicon.sendKeys(Keys.ENTER);
           return true;
        }
       return false;
        }*/


    //verify searched data of list report page
    public Boolean verifySearchTabledata() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(5000);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("caseIDsort"))));
        String col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";
        ArrayList<String> sample_ids = findlistofsampleID(col);
        button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("description"))));
        col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";

        boolean status = true;
        ArrayList<String> sample_des = findlistofsampleID(col);
        for (int i = 0; i < sample_ids.size(); i++) {
            if (sample_ids.get(i).toUpperCase().contains(this.searchdata.toUpperCase()) || sample_des.get(i).toUpperCase().contains(this.searchdata.toUpperCase()))
                ;
            else
                status = false;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("modulname")))).click();
        return status;
    }

    //verify searched data of list report page
    public Boolean verifyRefreshTabledata() throws Exception {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("datesort"))));
        if (button.isDisplayed())
            button.click();
        Thread.sleep(3000);
        String col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";
        ArrayList<String> datalist = findlistofsampleID(col);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        List<Date> datelist = new ArrayList<Date>();
        for (int i = 0; i < datalist.size(); i++) {
            //System.out.println(datalist.get(i));
            Date temp_date = formatter.parse(datalist.get(i));
            datelist.add(temp_date);
        }
        List copy1 = new ArrayList(datelist);
        Collections.sort(copy1, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        List copy2 = new ArrayList(copy1);
        Collections.reverse(copy2);
        boolean status = false;
        if (datelist.equals(copy1) || datelist.equals(copy2))
            status = true;
        System.out.println(datalist.size());

        return status;

    }

    //method for comaparision of two dates
    private boolean validateDateData() throws Exception {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("datesort"))));
        String col = "]/td[" + String.valueOf(this.findcolumnNumber(button)) + "]";
        ArrayList<String> datalist = findlistofsampleID(col);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        List<Date> datelist = new ArrayList<Date>();
        for (int i = 0; i < datalist.size(); i++) {
            //System.out.println(datalist.get(i));
            Date temp_date = formatter.parse(datalist.get(i));
            datelist.add(temp_date);
        }
        List copy1 = new ArrayList(datelist);
        Collections.sort(copy1, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        List copy2 = new ArrayList(copy1);
        Collections.reverse(copy2);
        boolean status = false;
        if (datelist.equals(copy1) || datelist.equals(copy2))
            status = true;
        System.out.println(datalist.size());
        return status;
    }

    //verify pagination 
    public Boolean verifyFuctionalityOfChevronIcons() throws Exception {
        props = PropertiesFile.prop;
        boolean status = true;
        for (int i = 1; i < 3; i++) {
            String expected_path = props.getProperty("chevronicon") + "[2]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expected_path))).click();
            Thread.sleep(3000);
            if (this.validateDateData() == false)
                status = false;

        }
        for (int i = 1; i < 3; i++) {
            String expected_path = props.getProperty("chevronicon") + "[1]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expected_path))).click();
            Thread.sleep(3000);
            if (this.validateDateData() == false)
                status = false;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("refreshicon")))).click();
        Thread.sleep(2000);
        return status;

    }


    //Verify sample assignee to the Reviewer
    public Boolean verifyReviewerassignee() throws Exception {
        props = PropertiesFile.prop;
        boolean status = false;
        int i = 1;
        String reportstatus = "Assigned, Report Updated, Report Generated";
        String table = props.getProperty("tablelocation");
        String status_text = props.getProperty("status_text");
        String status_path = "";
        List<WebElement> tableheaders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("tableheader"))));
        int k = tableheaders.size();

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("assign"))));
        int assignindex = this.findcolumnNumber(button);

        while (true) {
            status_path = table + String.valueOf(i) + "]/td[" + String.valueOf(k) + status_text;

            String statustext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status_path))).getText();
            if (reportstatus.contains(statustext)) {
                String temp_path = table + String.valueOf(i) + "]/td[" + String.valueOf(assignindex) + props.getProperty("selectassignee");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(temp_path))).click();
                List<WebElement> reviewerlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("reviewers"))));
                for (int j = 0; j < reviewerlist.size(); j++) {
                    if (reviewerlist.get(j).getText().toLowerCase().contains(props.getProperty("reviewer1").toLowerCase()) ||
                            reviewerlist.get(j).getText().toLowerCase().contains(props.getProperty("reviewer2").toLowerCase())) {
                        reviewerlist.get(j).click();
                        status = true;
                        break;
                    }
                }
            }
            if (status) break;
            i++;
            if (i > 10) {
                i = 1;
                String expected_path = props.getProperty("chevronicon") + "[2]";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expected_path))).click();
                Thread.sleep(3000);
            }
        }
        // open sample report page for assigend sample to the reviewer
//        if(status) {
//        	Thread.sleep(1000);    
//        	Actions action = new Actions(driver);
//        
//        	WebElement sample= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status_path)));
//        	action.moveToElement(sample).click().build().perform();
//        }
//    	status=wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.cssSelector(props.getProperty("cardonReportpage")))).isDisplayed();
//        Thread.sleep(5000);
        return status;
    }


    //Check for firstsample status color
    public String getfirstsampleStatuscolor() throws InterruptedException {
        props = PropertiesFile.prop;

        WebElement color = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(props.getProperty("firstsamplestatus"))));
        String colora = color.getCssValue("font-color");
        String[] hexValue = colora.replace("rgba(", "").replace(")", "").split(",");

        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);

        String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);

        if (actualColor.contains("#8FBC8F"))
            return "green";
        else
            return "red";


      /* props = PropertiesFile.prop;
        String firstsample_status = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(props.getProperty("firstsamplestatus")))).getText();
        System.out.println(firstsample_status);
        Thread.sleep(2000);
        return firstsample_status;   */
    }




}






    
    

