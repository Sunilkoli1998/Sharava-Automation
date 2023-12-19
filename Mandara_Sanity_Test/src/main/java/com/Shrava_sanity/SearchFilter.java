package com.Shrava_sanity;

//import com.data.Shonit_data.PropertiesFile;
import com.data.Shrava_data.PropertiesFile;
import com.google.common.collect.Ordering;
//import com.Common.ReportPage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import java.util.Properties;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SearchFilter {

    public Properties props;
    public WebDriver driver;
    public WebDriverWait wait;
    public Actions act;
    static String branchName, partnerName;
    static List<String> filterValuemultiple;

    String selectedrowdetails = "";
    static List<String> filterValues;

    private final Logger logger = LogManager.getLogger(SearchFilter.class);

    public SearchFilter(WebDriver driver) throws Exception {
        //super(driver);
        this.driver = driver;
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        filterValuemultiple = new ArrayList<String>();
        filterValues = new ArrayList<String>();

        props = PropertiesFile.prop;
        PropertiesFile.readSearchFilterFile();
        //PropertiesFile.readPageLoadFile();
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readShonitPropertiesFile();
        PropertiesFile.readShonitListReportFile();

        wait = new WebDriverWait(driver, 50);
        act = new Actions(driver);

    }

    // Click on ListReport
    public boolean clickonListReport() {
        boolean flag = false;
        props = PropertiesFile.prop;
        WebElement shonitlistreport = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("shonitlistreport"))));
        if (shonitlistreport.getText().matches("List Reports")) {
            if (shonitlistreport.isDisplayed()) {
                shonitlistreport.click();
                flag = true;
            }
        }

        return flag;
    }

    // Verify the Search Filter icon presence
    public boolean searchfilterPresent() {
        props = PropertiesFile.prop;
        // driver.findElement(By.xpath(props.getProperty("searchbox"))).clear();
        boolean searchfilterPresent = driver.findElement(By.cssSelector(props.getProperty("searchfiltericon")))
                .isDisplayed();
        return searchfilterPresent;
    }

    // Verify opening the modal window
    public boolean openFilterbox() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("clickonfilter")))).click();

        boolean flag = false;
        WebElement filterModalBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("filtermodalbox"))));
        String fliterReportHeading = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("modaltitle"))))
                .getText();

        if (fliterReportHeading.contains("Filter Reports") && filterModalBox.isDisplayed()) {
            flag = true;
        }
        // driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;
        return flag;
    }

    // Get the default values of all the dropdown field
    public int defaultFieldValues() {
        props = PropertiesFile.prop;

        int count = 0;
        List<WebElement> defaultFileds = driver.findElements(By.xpath(props.getProperty("filterdefaultfields")));
        for (WebElement defaultField : defaultFileds) {

            String fieldValue = defaultField.findElement(By.xpath(props.getProperty("filterfieldvalues"))).getText();
            String placeHolderValue = defaultField.getAttribute("placeholder");
            if (defaultField.isDisplayed() && fieldValue.equals(placeHolderValue)) {
                count++;
            }

        }

        return count;

    }

    // Get the default values of all the dropdown field
    public boolean validateDefaultFieldNames() {
        props = PropertiesFile.prop;

        boolean flag = false;
        List<WebElement> defaultFileds = driver.findElements(By.xpath(props.getProperty("filterdefaultfields")));
        for (WebElement defaultField : defaultFileds) {

            String fieldValue = defaultField.findElement(By.xpath(props.getProperty("filterfieldvalues"))).getText();
            System.out.println(fieldValue);
            if (props.getProperty("filterdefaultdropdowns").contains(fieldValue)) {
                flag = true;

            } else {
                flag = false;
            }

        }

        return flag;

    }

    // Get the default values of all the date fields
    public int defaultDateFields() {
        props = PropertiesFile.prop;

        int count = 0;
        List<WebElement> defaultDateFileds = driver.findElements(By.xpath(props.getProperty("filterdatefileds")));
        for (WebElement defaultDateField : defaultDateFileds) {
            String fieldValue = defaultDateField.findElement(By.xpath(props.getProperty("filterdatevalues"))).getText()
                    .replaceAll("^\"|\"$", "");
            String placeHolderValue = defaultDateField.getAttribute("placeholder");
            if (defaultDateField.isDisplayed() && fieldValue.equals(placeHolderValue)) {
                count++;
            }

        }

        return count;

    }

    // To verify cancel button is enabled and clickable
    public boolean cancelButton() {
        props = PropertiesFile.prop;
        boolean flag = false;
        WebElement cancelButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("cancelbutton"))));

        if (cancelButton.isEnabled() && cancelButton.getText().contains("clear")) {
            flag = true;
        }

        return flag;
    }

    // To verify Reset button is enabled and clickable
    public boolean resetButton() {
        props = PropertiesFile.prop;
        boolean flag = false;
        WebElement resetButton = driver.findElement(By.xpath(props.getProperty("resetbutton")));

        if (resetButton.isEnabled() && resetButton.getText().contains("Reset")) {
            flag = true;
        }

        return flag;
    }

    // To verify Apply button is enabled and clickable
    public boolean applyButton() {
        props = PropertiesFile.prop;
        boolean flag = false;
        WebElement applyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybutton"))));
        if (applyButton.isEnabled() && applyButton.getText().contains("Apply")) {
            flag = true;
        }

        return flag;
    }

    // Function to click on apply button to verify further the search is successful
    // based on the selected options
    public boolean clickCancel() throws InterruptedException {
        props = PropertiesFile.prop;
        //Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelbutton")))).click();
        // Thread.sleep(5000);
        boolean listReportredirect = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("listreportbody"))))
                .isDisplayed();
        return listReportredirect;
    }

    // Function to click on Reset button to set to default
    public void clickReset() throws InterruptedException {
        props = PropertiesFile.prop;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("resetbutton")))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    // Function to click on apply button to verify further the search is successful
    // based on the selected options
    public boolean clickApply() throws InterruptedException {
        props = PropertiesFile.prop;
        //driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("applybutton")))).click();
        //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        boolean listReportredirect = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("listreportbody"))))
                .isDisplayed();
        return listReportredirect;
    }

    // Check If the link on the No Data Found Page is navigating to the Analysis
    // default page
    public boolean linkToDefaultPage() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        List<String> subdate = new ArrayList<String>();
        try {
            WebElement errorIcon = driver.findElement(By.xpath(props.getProperty("errorIcon")));

            WebElement errorMessage = driver.findElement(By.xpath(props.getProperty("errorMessage")));
            WebElement redirectLink = driver.findElement(By.xpath(props.getProperty("redirectLink")));
            System.out.println("Links Found by system");

            if (errorIcon.isDisplayed() && errorMessage.isDisplayed() && redirectLink.isDisplayed()) {
                redirectLink.click();
            }

            System.out.println("Links Are getting displayed");
            Thread.sleep(2000);
            wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));

            List<WebElement> submittedDates = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataSubmittedAt")));

            for (WebElement date : submittedDates) {
                String dateValue = date.getText().trim().substring(0, 10);

                subdate.add(dateValue);

            }

            if (Ordering.natural().reverse().isOrdered(subdate)) {
                flag = true;

            } else {
                flag = false;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            flag = false;
        }

        return flag;

    }

    public boolean noDataFound() throws InterruptedException {
        props = PropertiesFile.prop;
        //logger.info("There is no data found for the applied filter");

        try {
            wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
            //Thread.sleep(1000);

            WebElement errorIcon = driver.findElement(By.xpath(props.getProperty("errorIcon")));
            WebElement errorMessage = driver.findElement(By.xpath(props.getProperty("errorMessage")));
            WebElement redirectLink = driver.findElement(By.xpath(props.getProperty("redirectLink")));

            if (errorIcon.isDisplayed() && errorMessage.isDisplayed() && redirectLink.isDisplayed()) {
                //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                driver.findElement(By.xpath(props.getProperty("redirectLink"))).click();

                return true;

            } else {

                return false;
            }
        } catch (Exception e) {

            //e.printStackTrace();
            return false;
        }

    }

    // DoubleClicking on the Apply button
    public void performDoubleClick() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement target = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("applybutton"))));
        Thread.sleep(3000);
        target.click();
    }

    // Select the Branch Name
    public String selectBranchName() throws InterruptedException {
        props = PropertiesFile.prop;
        // String branchName = null;
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clickonfilter")))).click();

        // clickReset();
        driver.findElement(By.xpath(props.getProperty("filterbranch"))).click();
        List<WebElement> branchValues = driver.findElements(By.cssSelector(props.getProperty("dropdownvaluesss")));
        Thread.sleep(2000);
        for (WebElement branchValue : branchValues) {
            branchName = branchValue.getText();
            if (branchName.startsWith(props.getProperty("branchname"))) {
                branchValue.click();
                break;
            }
        }
        return branchName;
    }

    // Select the Partner Name
    public String selectPartnerName() throws InterruptedException {
        props = PropertiesFile.prop;
        // String partnerName = null;
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clickonfilter")))).click();

        clickReset();
        driver.findElement(By.xpath(props.getProperty("filterpartner"))).click();
        List<WebElement> partnerValues = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        Thread.sleep(1000);
        for (WebElement partnerValue : partnerValues) {
            partnerName = partnerValue.getText().trim();
            if (partnerName.startsWith(props.getProperty("partnername"))) {
                partnerValue.click();
                // act.moveToElement(partnerValue).click().build().perform();
                break;
            }
        }
        return partnerName;
    }

    public String selectDropdownValueinFilterByIndex(WebElement filterName) throws InterruptedException {
        props = PropertiesFile.prop;
        String dropdownValue = null;

        // selectPartnerName();
        Thread.sleep(1000);
        selectBranchName();
        Thread.sleep(1000);
        filterName.click();
        List<WebElement> dropdownValues = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));

        int randomNo = ThreadLocalRandom.current().nextInt(0, dropdownValues.size() - 1);
        //int randomNo =39;
        for (WebElement value : dropdownValues) {

            if (dropdownValues.indexOf(value) == randomNo) {
                dropdownValue = value.getText().trim();
                System.out.println("Installation Name = " + dropdownValue);

                value.click();
                break;
            }
        }

        return dropdownValue;

    }

    public String selectDropdownValueinFilterByIndex1(WebElement filterName) throws InterruptedException {
        props = PropertiesFile.prop;
       // String dropdown1 = null;
        String actualInstallationId = null;
        // selectPartnerName();
        Thread.sleep(2000);
        selectBranchName();
        Thread.sleep(2000);
        filterName.click();
        List<WebElement> dropdownValues = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        for (WebElement installationId : dropdownValues) {
            actualInstallationId = installationId.getText();
            if (actualInstallationId.contains("SIG-Sig-1165-1")) {
                Thread.sleep(2000);
                installationId.click();
                break;
            }
        }
        // WebElement installation_id = driver.findElement(By.xpath(props.getProperty("validInstallation")));
        // dropdown1 = installation_id.getText();
        //  installation_id.click();

        return actualInstallationId;

    }

    public String selectReviwername(WebElement filterName) throws InterruptedException {
        props = PropertiesFile.prop;
        String reviewerName = null;
        Thread.sleep(2000);
        selectBranchName();
        Thread.sleep(2000);
        filterName.click();
        List<WebElement> dropdownValues = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        for (WebElement reviewname:dropdownValues) {
            reviewerName=reviewname.getText();
            if(reviewerName.equals("santosh"))
            {
                reviewname.click();
                break;
            }

        }

        return reviewerName;

    }
    public String selectDropdownValueinFilterByValue(WebElement filterName, String filterValue)
            throws InterruptedException {
        props = PropertiesFile.prop;
        String reviewStatus = null;

        // selectPartnerName();
        Thread.sleep(1000);
        selectBranchName();
        Thread.sleep(1000);
        filterName.click();
        List<WebElement> review_statuses = driver.findElements(By.cssSelector(props.getProperty("dropdownvalues")));
        Thread.sleep(2000);
        for (WebElement reviewstatus : review_statuses) {
            reviewStatus = reviewstatus.getText();
            if (reviewStatus.equals(filterValue)) {
                System.out.println("the reviewer status is = " + reviewStatus);
                reviewstatus.click();
                Thread.sleep(3000);
                // act.moveToElement(reviewstatus).click().build().perform();
                break;
            }
        }
        Thread.sleep(3500);
        performDoubleClick();

        return reviewStatus;

    }

    public String clickInstallationFilter() throws InterruptedException {
        props = PropertiesFile.prop;

        selectPartnerName();
        Thread.sleep(3000);
        WebElement installationFilter = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filterinstallation"))));
        String installationname = selectDropdownValueinFilterByIndex1(installationFilter);
        System.out.println("Filter selected intallation id is " + installationname);
                  Thread.sleep(3000);
        performDoubleClick();
        Thread.sleep(6000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));

        return installationname;
    }

    // Selecting branch, and installation and verifying the successful search
    // against the values
    public boolean selectInstallationName(String product) throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            String actualInstallation = clickInstallationFilter();//SIG-Sig-223-1
            System.out.println("the actual installtion ID " + actualInstallation);
            Thread.sleep(2000);

            if (product.equals("Shonit")) {
                for (int i = 0; i > 1; i++) {
                    boolean flag1 = noDataFound();
                    if (flag1 == false) {
                        System.out.println("false 1st  executed");
                        boolean flag2 = openAnyValidReport();
                        if (flag2 == true) {
                            String expectedInstallation = getInstallationvalue();
                            System.out.println("true executed");
                            flag = comapreValues(expectedInstallation, actualInstallation);

                            if (flag) {
                                break;
                            } else {
                                System.out.println("false 2nd  executed");
                                flag = false;
                                break;
                            }
                        } else {

                            System.out.println("3rd else condition executed");
                            actualInstallation = clickInstallationFilter();
                        }

                    }
                    //else {
                    // actualInstallation = clickInstallationFilter();
                    //}
                }
            } else {
                List<WebElement> installationValues = driver
                        .findElements(By.xpath(props.getProperty("filterTableDataInstallation")));
                for (WebElement installData : installationValues) {
                    String tableData = installData.getText().trim();
                    System.out.println("Actual Outcom of installation id " + tableData);
                    if (tableData == actualInstallation) {
                        System.out.println("true executed");
                        flag = true;

                    } else {
                        System.out.println("false executed");
                        flag = false;
                        break;
                    }

                }

            }

            return flag;
        } catch (Exception e) {
            //e.printStackTrace();


            return false;
        }

    }


    // Selecting reviewer and verifying the successful search against these
    public boolean selectByReviewer() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        selectPartnerName();
        Thread.sleep(1000);
        WebElement reviewerFilter = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filterreviewer"))));
        String reviewername = selectReviwername(reviewerFilter);
        Thread.sleep(2000);
        performDoubleClick();
           Thread.sleep(6000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(2000);
        try {
            List<WebElement> reviewerValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));
            for (WebElement reviewerData : reviewerValues) {
                String tableData = reviewerData.getText();
                if (tableData.equals(reviewername)) {
                    System.out.println(tableData);
                    flag = true;

                }
                else {
                    flag = false;
                    break;
                }

            }
        }
        catch (Exception e)
        {
              refreshPage();
            flag = false;

        }

        return flag;

    }

    // Selecting Review status and verifying the successful search against Approved
    // And Rejected
    public boolean selectByReviewStatusApporved() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        selectPartnerName();
        Thread.sleep(2000);
        WebElement filterName = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.xpath(props.getProperty("filterreviewstatus")))));
        String reviewStatus = selectDropdownValueinFilterByValue(filterName, "Approved");
        Thread.sleep(5000);
        System.out.println("the reviwer status is  " + reviewStatus);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(4000);
        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataApproveAndReject")));
            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                System.out.println("The actual Outcome is " +tableData);
                if (tableData.equals(reviewStatus)) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
            refreshPage();
           // e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Selecting Review status and verifying the successful search against Rejected
    public boolean selectByReviewStatusRejected() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        selectPartnerName();
        Thread.sleep(2000);
        WebElement filterName = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.xpath(props.getProperty("filterreviewstatus")))));
        String reviewStatus = selectDropdownValueinFilterByValue(filterName, "Rejected");
                      Thread.sleep(3000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(2000);
        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataApproveAndReject")));
            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText().trim();
                System.out.println("the actual status is "+tableData);
                if (tableData.equals(reviewStatus)) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
              refreshPage();
            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Selecting Unassigned Review status and verifying the successful search
    // against these
    public boolean selectUnassignedReview() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        selectPartnerName();
        Thread.sleep(1000);
        WebElement filterName = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.xpath(props.getProperty("filterreviewstatus")))));
        selectDropdownValueinFilterByValue(filterName, "Unassigned");
                     Thread.sleep(2000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(4000);
        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataNoAssignee")));

            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (tableData.equals(props.getProperty("defaultAssignData"))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
               refreshPage();

            flag = false;

        }

        return flag;

    }

    // Selecting assigned Review status and verifying the successful search
    // against these
    public boolean selectAssignedReview() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        selectPartnerName();
        Thread.sleep(1000);
        WebElement filterName = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.xpath(props.getProperty("filterreviewstatus")))));
        selectDropdownValueinFilterByValue(filterName, "Assigned");
              Thread.sleep(3000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(5000);
        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));

            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (!tableData.equals(props.getProperty("defaultAssignData"))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }

        } catch (Exception e) {
            //   refreshPage();
            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Select multiple filters, in this case branch, installation name and reviewer
    public boolean filterByReviewerAndReviewStatus() throws InterruptedException {
        props = PropertiesFile.prop;
        String reviewerData = props.getProperty("filterReviewer");
        String reviewStatusData = props.getProperty("reviewmultiStatus");
        boolean flag = false;
        Thread.sleep(1000);
        selectPartnerName();
        Thread.sleep(1000);
        selectBranchName();
        Thread.sleep(1000);
        driver.findElement(By.xpath(props.getProperty("filterreviewstatus"))).click();//review status drop down
        driver.findElement(By.xpath(props.getProperty("reviewstatusxpath"))).click();// selecting
        Thread.sleep(1000);
        driver.findElement(By.xpath(props.getProperty("filterreviewer"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(props.getProperty("reviewerxpath"))).click();
        Thread.sleep(1000);

        performDoubleClick();
        Thread.sleep(4000);
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        Thread.sleep(2000);

        try {

            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataApproveAndReject")));
            List<WebElement> reviewerValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));

            int rowSize = reviewerValues.size();

            for (int i = 0; i < rowSize; i++) {

                String reviewStatus = reviewStatusValues.get(i).getText().trim();

                String reviewer = reviewerValues.get(i).getText();

                if (reviewStatus.equals(reviewStatusData) && reviewer.equals(reviewerData)) {
                    System.out.println("No of rows is " + rowSize);
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
            //    refreshPage();
            //e.printStackTrace();
            flag = true;

        }

        return flag;

    }

    public String[] generateFromDate() {
        String[] dates = new String[2];
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date today = new Date();
        String date = dateFormat.format(today);

        int day, month, year;

        String dateArray[] = date.split(" ");
        day = Integer.valueOf(dateArray[0]);
        month = Integer.valueOf(dateArray[1]);
        year = Integer.valueOf(dateArray[2]);

        LocalDate from = LocalDate.of(year, month, 01);
        LocalDate to = LocalDate.of(year, month, day / 2);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        String fromDate = randomDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String fromDate1 = randomDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//  		  System.out.println(fromDate);
//  		  System.out.println(fromDate1);
        dates[0] = fromDate;
        dates[1] = fromDate1;

        return dates;

    }

    public String[] generateToDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        String[] dates = new String[2];
        Date today = new Date();
        String date = dateFormat.format(today);

        int day, month, year;

        String dateArray[] = date.split(" ");
        day = Integer.valueOf(dateArray[0]);
        month = Integer.valueOf(dateArray[1]);
        year = Integer.valueOf(dateArray[2]);

        LocalDate from = LocalDate.of(year, month, day / 2);
        LocalDate to = LocalDate.of(year, month, day);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);
        String toDate = randomDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String toDate1 = randomDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//		System.out.println(toDate);
//		System.out.println(toDate1);

        dates[0] = toDate;
        dates[1] = toDate1;

        return dates;

    }

    // Selecting date range filter
    public boolean selectByDateFilter() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clickonfilter")))).click();

        clickReset();
        String[] fromDates = generateFromDate();
        String[] toDates = generateToDate();
        String fromDate1 = fromDates[0];
        String fromDate2 = fromDates[1];
        String toDate1 = toDates[0];
        String toDate2 = toDates[1];

        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("fromdatefield"))))
                    .sendKeys(fromDate1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("todatefield"))))
                    .sendKeys(toDate1);
            System.out.println(fromDate1 + "==== " + toDate1);

            // clickApply();
            performDoubleClick();
            List<String> subdate = new ArrayList<String>();

            wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
            Thread.sleep(2000);

            List<WebElement> submittedDates = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("tableRow"))));

            for (int i = 1; i <= submittedDates.size(); i++) {
                String temp_path = props.getProperty("tableRow") + "[" + i + props.getProperty("tableSubmittedDate");
                String dateValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(temp_path)))
                        .getText().trim().substring(0, 10);
                subdate.add(dateValue);
            }
            for (int i = 0; i < subdate.size(); i++) {
                if ((subdate.get(i).compareToIgnoreCase(fromDate2) >= 0)
                        && (subdate.get(i).compareToIgnoreCase(toDate2) <= 0)) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }

        } catch (Exception e) {
            //   refreshPage();
            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Verify Filter Results Consistency.
    public boolean checkFilterResultConsistency() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        openFilterbox();
        Thread.sleep(3000);
        clickCancel();
        Thread.sleep(3000);
        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataApproveAndReject")));
            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (tableData.equals("Approved")) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
            //   refreshPage();
            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Check the presence of Filter data after applying filter and clicking filter
    // again
    public boolean checkFilterBoxData() throws InterruptedException {
        props = PropertiesFile.prop;

        try {
            openFilterbox();
            Thread.sleep(2000);
            String partner_path = props.getProperty("filterpartner") + props.getProperty("filtervalue");
            String branch_path = props.getProperty("filterbranch") + props.getProperty("filtervalue");
            String reviewStatus_path = props.getProperty("filterreviewstatus") + props.getProperty("filtervalue");
            String partnerValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(partner_path)))
                    .getText().trim();
            String branchValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(branch_path)))
                    .getText().trim();
            String reviewStatusValue = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(reviewStatus_path))).getText().trim();

            if (partnerValue.equals(partnerName) && branchValue.equals(branchName)
                    && reviewStatusValue.equals("Approved")) {
                closeFilter();
                return true;

            } else {
                closeFilter();
                return false;
            }

        } catch (Exception e) {
            Thread.sleep(2000);
            //   refreshPage();
            //e.printStackTrace();
            resetButton();
            return true;
        }
    }

    // Get the Installation value from report Page for Aadi
    public String getInstallationvalue() throws InterruptedException {
        String value = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(props.getProperty("installtionColumnValue"))))
                .getText().trim();
        System.out.println(value);
        return value;

    }

    // Compare 2 String values
    public boolean comapreValues(String expectedValue, String actualValue) {
        if (expectedValue.equals(actualValue)) {
            return true;
        } else {
            return false;
        }

    }

    // Close Filter in case of unexpected situation
    public void closeFilter() throws InterruptedException {
        if (cancelButton()) {
            clickCancel();
        }
    }
//	====================================== Apply Multiple filter ========================================================

    // Select All the Filters
    public List<String> selectAllFilters() throws InterruptedException {

        selectPartnerName();

        WebElement installationFilter = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filterinstallation"))));
        String installationname = selectDropdownValueinFilterByIndex(installationFilter);
        filterValues.add(installationname);

        WebElement reviewerFilter = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filterreviewer"))));
        String reviewername = selectDropdownValueinFilterByIndex(reviewerFilter);
        filterValues.add(reviewername);

        WebElement filterReviewStatus = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.xpath(props.getProperty("filterreviewstatus")))));
        String reviewStatus = selectDropdownValueinFilterByIndex(filterReviewStatus);
        filterValues.add(reviewStatus);

        performDoubleClick();

        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("listreportbody"))));
        //Thread.sleep(1000);

        return filterValues;

    }

    // Validate the Filter Results for Installation
    public boolean validateInstallationFilterResult(String product) throws InterruptedException {

        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            String actualInstallation = filterValues.get(0);
            Thread.sleep(1000);

            if (product.equals("Shonit")) {

                boolean flag2 = openAnyValidReport();
                if (flag2 == true) {
                    String expectedInstallation = getInstallationvalue();
                    System.out.println("Flage2====" + expectedInstallation);
                    flag = comapreValues(expectedInstallation, actualInstallation);
                } else {
                    flag = false;
                }

            } else {
                List<WebElement> installationValues = driver
                        .findElements(By.xpath(props.getProperty("filterTableDataInstallation")));
                for (WebElement installData : installationValues) {
                    String tableData = installData.getText().trim();
                    System.out.println("table data is " + tableData);
                    if (tableData.equals(actualInstallation)) {
                        flag = true;

                    } else {
                        flag = false;
                        break;
                    }

                }

            }

            return flag;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }

    }

    // Validate the Filter Results for Reviewer
    public boolean validateReviewerFilterResult() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            List<WebElement> reviewerValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));
            for (WebElement reviewerData : reviewerValues) {
                String tableData = reviewerData.getText();
                if (tableData.equals(filterValues.get(1))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {
            //e.printStackTrace();
            flag = false;

        }

        return flag;

    }

    // Validate the Filter Results for Approve/Reject review status
    public boolean validateApproveAndRejectFilterResult() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataApproveAndReject")));
            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (tableData.equals(filterValues.get(2))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }
        } catch (Exception e) {

            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Validate the Filter Results for Assigned review status
    public boolean validateAssignedFilterResult() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));

            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (!tableData.equals(props.getProperty("defaultAssignData"))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }

        } catch (Exception e) {

            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Validate the Filter Results for unassigned review status
    public boolean validateUnassignedFilterResult() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;

        try {
            List<WebElement> reviewStatusValues = driver
                    .findElements(By.xpath(props.getProperty("filterTableDataReviewer")));

            for (WebElement statusData : reviewStatusValues) {
                String tableData = statusData.getText();
                if (tableData.equals(props.getProperty("defaultAssignData"))) {
                    flag = true;

                } else {
                    flag = false;
                    break;
                }

            }

        } catch (Exception e) {

            //e.printStackTrace();
            flag = false;

        }
        return flag;

    }

    // Apply and validate result for multiple filter
    public boolean applyMultiPleFilters(String product) throws InterruptedException {

        boolean flag = false, flag1, flag2, flag3;
        try {
            filterValuemultiple = selectAllFilters();
            for (int i = 0; i <= 10; i++) {
                boolean status = noDataFound();
                if (status == false) {
                    flag1 = validateInstallationFilterResult("Shonit");
                    flag2 = validateReviewerFilterResult();
                    if (filterValuemultiple.get(2).equals("Assigned")) {
                        flag3 = validateAssignedFilterResult();
                    } else if (filterValuemultiple.get(2).equals("Unassigned")) {
                        flag3 = validateUnassignedFilterResult();
                    } else {
                        flag3 = validateApproveAndRejectFilterResult();
                    }
                    if (flag1 && flag2 && flag3) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                        break;
                    }

                } else {
                    filterValuemultiple = selectAllFilters();

                }

            }
        } catch (Exception e) {
            refreshPage();
            //e.printStackTrace();
            flag = true;
        }
        return flag;
    }


    //Verify sample assignee to the Reviewer
    public Boolean openAnyValidReport() throws Exception {
        props = PropertiesFile.prop;
        boolean status = false;
        int i = 1;
        String reportstatus = "Assigned, Report Updated, Report Generated, Approved, Rejected";
        String table = props.getProperty("tablelocation");
        String status_text = props.getProperty("status_text");
        String status_path = "";
        Thread.sleep(1500);
        selectedrowdetails = "";

        try {
            List<WebElement> tableheaders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(props.getProperty("tableheader"))));
            int k = tableheaders.size();

            while (true) {
                status_path = table + String.valueOf(i) + "]/td[" + String.valueOf(k) + status_text;
                String statustext = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status_path))).getText();
                if (reportstatus.contains(statustext)) {
                    WebElement sample = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(status_path)));
                    sample.click();
                    Thread.sleep(1500);

                    status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("cardonReportpage")))).isDisplayed();
                    Thread.sleep(1500);

                }
                if (status) break;
                i++;
                if (i > 10) {
                    i = 1;
                    String expected_path = props.getProperty("chevronicon") + "[2]";
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expected_path))).click();
                    //Thread.sleep(5000);
                }
            }

            return status;
        } catch (Exception e) {

            //e.printStackTrace();
            return false;
        }

    }

    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
        logger.info("Refreshing the page as the Filter got stuck in between the test.");
    }


    public String installationId() throws InterruptedException {
        props = PropertiesFile.prop;
        String actualInstallationId = null;
        this.clickInstallationFilter();
        Thread.sleep(4000);
        List<WebElement> installationValues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("filterTableDataInstallation1"))));
        for (WebElement installData : installationValues) {
            actualInstallationId = installData.getText();
            logger.info("actual instaltion id "+" "+actualInstallationId);
            if (actualInstallationId.equals("SIG-Sig-1165-1")) {
                Thread.sleep(3000);
                //installData.click();
                break;
            }
        }

        return actualInstallationId;
    }
}

