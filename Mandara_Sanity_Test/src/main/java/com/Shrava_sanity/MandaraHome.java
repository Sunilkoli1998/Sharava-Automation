package com.Shrava_sanity;


import com.data.Shrava_data.PropertiesFile;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.lang.String;
import java.util.concurrent.TimeUnit;

public class MandaraHome extends Login {

    public Properties props;
    public WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(MandaraHome.class);


    public MandaraHome(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        props = PropertiesFile.prop;
        wait = new WebDriverWait(driver, 40);
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readPropertiesFIle();
    }

    //verify mandara home page is opened
    public boolean checkMadaraHomePage() throws InterruptedException {
        WebElement apptoolbar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("apptoolbar"))));
        if (apptoolbar.getText().contains(props.getProperty("mandaraheader"))) {
            return true;
        } else
            return false;
    }

    //verify mini Sigtuple Icon
    public String checkSigtupleIcon() throws InterruptedException {
        String sigtupleicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("sigtupleicon")))).getAttribute("alt");
        return sigtupleicon;
    }

    //verify User Icon
    public boolean checkusericon() throws InterruptedException {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("usericon")))).isDisplayed();
    }

    //verify all options after click on user icon
    public String verifyOptionFromUserIcon() throws InterruptedException {
        WebElement usericon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("usericon"))));
        String options = "";
        if (usericon.isDisplayed()) {
            usericon.click();
            Thread.sleep(2000);
            List<WebElement> optionslist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("usericonoptions"))));
            for (WebElement temp : optionslist)
                options = options + temp.getText() + " ";
            System.out.println(options);
            Actions action = new Actions(driver);
            action.moveToElement(usericon).click().build().perform();
            //driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            Thread.sleep(3000);
        }

        return options;
    }

    //To verify Terms of Services functionality and contents
    public boolean termsNCondTest() throws InterruptedException {
        WebElement termsNCond = driver.findElement(By.xpath(props.getProperty("termsNCond")));
        boolean status = false;
        if (termsNCond.isEnabled()) {
            termsNCond.click();
            Assert.assertTrue(driver.findElement(By.cssSelector(props.getProperty("termsNCondDialog"))).isDisplayed());
            Assert.assertEquals(driver.findElement(By.xpath(props.getProperty("termsNCondHeader"))).getText(), "Terms of Service");
            status = true;
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Thread.sleep(2000);
        driver.findElement(By.cssSelector(props.getProperty("closetermsbox"))).click();
        return status;
    }

    //To verify the Privacy Policy functionality
    public boolean privacyPolicy() throws InterruptedException {
        WebElement privacypol = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("privacypol"))));
        if (privacypol.isDisplayed() && privacypol.getText().contains("Privacy Policy")) {
            Actions action = new Actions(driver);
            action.moveToElement(privacypol).click().build().perform();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            // Thread.sleep(2000);
            String currentWindowHandle = driver.getWindowHandle();
            Set<String> windowhandlers = driver.getWindowHandles();
            for (String s : windowhandlers)
                if (!(s.equals(currentWindowHandle)))
                    driver.switchTo().window(s);
        }
        boolean status = driver.getCurrentUrl().contains("privacy");
        driver.close();
        return status;
    }

    //verify number of products
    public int numberOfProductsForUser() throws InterruptedException {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("numberofProductsCard")))).size();

    }

    //verify all products names
    public String getProductnames() throws InterruptedException {
        String product = "";
        List<WebElement> optionslist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("nameofproducts"))));
        for (WebElement temp : optionslist)
            product = product + temp.getText() + " ";
        System.out.println(product);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(1000);
        return product;
    }

    private boolean findproducticon(String cardpath) throws InterruptedException {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardpath + props.getProperty("image_onproductcard")))).isDisplayed();
    }

    private boolean Allreporttype(String cardpath) throws InterruptedException {
        String report = "";
        List<WebElement> optionslist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cardpath + props.getProperty("reporttypes_onproductcard"))));
        for (WebElement temp : optionslist)
            report = report + temp.getText();
        System.out.println(report);
        return report.equalsIgnoreCase(props.getProperty("reporttypes_onproductcard_text"));
    }

    private boolean gotolistreportlink(String cardpath) throws InterruptedException {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardpath + props.getProperty("gotolist_onproductcard")))).isDisplayed();
    }

    private boolean reportlinks(String cardpath) throws InterruptedException {
        int size = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cardpath + props.getProperty("reportlinks_onproductcard")))).size();
        return size == 3;
    }

    //verify mini Sigtuple Icon
    public boolean verifyCardDetails(String cardpath) throws InterruptedException {
        WebElement productcard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cardpath)));
        boolean status = false;
        if (productcard.isDisplayed()) {
            status = this.findproducticon(cardpath);
            status = status && this.Allreporttype(cardpath);
            status = status && this.gotolistreportlink(cardpath);
            status = status && this.reportlinks(cardpath);
            if (status) {
                logger.info(" all details of this card is verified ");
            } else
                logger.info("all details of this card are not matched");
        }
        return status;
    }

    private boolean verifycount(WebElement link) throws InterruptedException {
        int cardcount = 0;
        int noofreport = 0;
        String totalpage = "0";
        if (link.isDisplayed()) {
            cardcount = Integer.valueOf(link.getText());
            //System.out.println(cardcount);
            link.click();
            Thread.sleep(3000);
            try {
                WebElement close = driver.findElement(By.cssSelector(props.getProperty("closeappabout")));
                if (close.isDisplayed()) {
                    close.click();
                }
            } catch (Exception e) {
            }
            WebElement reportpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("listreportpage"))));
            if (reportpage.isDisplayed()) {
                Thread.sleep(2000);
//    			while(true) {
//    				try {
//    					WebElement next=driver.findElement(By.xpath(props.getProperty("nextpageicon")));
//    					if(next==null)
//    						break;
//    					else {
//    						next.click();
//    						Thread.sleep(1000);
//    						tempcount=tempcount+1;
//    						}
//    				}catch(Exception e) {break;}
//    			}
                WebElement totalnoofpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("totalnoofpage"))));
                if (totalnoofpage.isDisplayed()) {
                    String text = totalnoofpage.getText();
                    System.out.println("total number of pages " + text);
                    totalpage = text.split("of")[1].trim();
                    WebElement inputfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("pageinputarea"))));
                    if (inputfield.isDisplayed()) {
                        inputfield.click();
                        inputfield.clear();
                        Thread.sleep(1000);
                        inputfield.sendKeys(totalpage);
                        Thread.sleep(1000);
                        System.out.println("this case is passed");
                        inputfield.sendKeys(Keys.ENTER);
                        Thread.sleep(3000);

                    }
                }
            }
        }
        Thread.sleep(2000);
        try {
            int temppage = Integer.valueOf(totalpage) - 1;
            if (temppage < 0)
                temppage = 0;
            for (int i = 0; i < 3; i++) {
                System.out.println("number of tempage are " + temppage);
                int count = driver.findElements(By.xpath(props.getProperty("noofreportsrow"))).size();
                //System.out.println(count);
                noofreport = temppage * 10 + (driver.findElements(By.xpath(props.getProperty("noofreportsrow"))).size() / 2);
                System.out.println("number of report are " + noofreport);
                if (cardcount == noofreport)
                    break;
                else
                    Thread.sleep(2000);
            }
        } catch (Exception e) {
        }
        System.out.println(noofreport);
        driver.findElement(By.xpath(props.getProperty("sigtupleicon"))).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//div[@class='entry-text']")

        System.out.println(String.valueOf(cardcount) + " : " + String.valueOf(noofreport));
        return (cardcount == noofreport);
    }

    public boolean verifyReportCount(String productpath) throws InterruptedException {
        int size = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(productpath + props.getProperty("reportlinks_onproductcard")))).size();
        boolean status = true;
        for (int i = 0; i < size; i++) {
            List<WebElement> reportlink = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(productpath + props.getProperty("reportlinks_onproductcard"))));
            status = status && verifycount(reportlink.get(i));
            System.out.println(status);
            Thread.sleep(1000);
        }

        return status;

    }

    //----------------------------------- Side Menu -------------------------------
    //verify side menu icons
    public boolean checkSideMenu() throws InterruptedException {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("sidemenu")))).isDisplayed();

    }

    //verify all products names
    private boolean clickonsidemenu() throws InterruptedException {
        WebElement sidemenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("sidemenu"))));
        if (sidemenu.isDisplayed()) {
            sidemenu.click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //Thread.sleep(1000);
            return true;
        }
        return false;
    }

    //verify all products names
    public String getProductnamesfromsidemenu() throws InterruptedException {
        String product = "";
        if (this.clickonsidemenu()) {
            List<WebElement> optionslist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("listofoptions"))));
            for (WebElement temp : optionslist)
                product = product + temp.getText() + " ";
            System.out.println(product);
            // driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("selectdashboard")))).click();
            Thread.sleep(2000);
        }
        return product;
    }

    //verify all products names
    public boolean selectproduct(String product) throws InterruptedException {
        if (this.clickonsidemenu()) {
            WebElement selectoption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("selectoption") + product + "')]")));
            if (selectoption.isDisplayed()) {
                selectoption.click();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                //Thread.sleep(3000);
                try {
                    WebElement close = driver.findElement(By.cssSelector(props.getProperty("closeappabout")));
                    if (close.isDisplayed()) {
                        close.click();
                    }
                } catch (Exception e) {
                }
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                //Thread.sleep(1000);
                WebElement reportpage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("listreportpage"))));
                if (reportpage.isDisplayed())
                    driver.navigate().back();
                return true;
            }
        }
        return false;
    }

    //Verify Home Page reload or navigation to home page from any window
    public void reloadonIconClick() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("sigtupleicon")))).click();
        if (driver.findElement(By.xpath(props.getProperty("selectproduct"))).isDisplayed()) {
            return;
        }
    }

    //To get the count of number of products on the top bar
    public int numberofProducts() throws Exception {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("numberofProductsonTopBar")))).size();
    }

    //To get the names of all products present on top bar
    public String productOnTopBar() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("productonTopBar")))).getText();
    }


    //To verify clicking on Shonit navigates to Shonit List Report
    public String clickOnShonitfromTopBar() {
        driver.findElement(By.xpath(props.getProperty("rowheader"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("shonitontop")))).click();
        String shonitURL = driver.getCurrentUrl();
        reloadonIconClick();
        return shonitURL;
    }

    //To verify clicking on shrava navigates to shrava List Report
    public String clickOnShravafromTopBar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("shravaontop")))).click();
        String shravaURL = driver.getCurrentUrl();
        reloadonIconClick();
        return shravaURL;
    }

    //To verify clicking on Drishti navigates to Drishti List Report
    public String clickOnDrishtifromTopBar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("drishtiontop")))).click();
        String drishtiURL = driver.getCurrentUrl();
        reloadonIconClick();
        return drishtiURL;
    }

    //To verify clicking on Aadi navigates to Aadi List Report
    public String clickOnAadifromTopBar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("aadiontop")))).click();
        String aadiURL = driver.getCurrentUrl();
        reloadonIconClick();
        return aadiURL;
    }

    //To verify clicking on CXR navigates to CXR List Report
    public String clickOnCXRfromTopBar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cxrontop")))).click();
        String cxrURL = driver.getCurrentUrl();
        reloadonIconClick();
        return cxrURL;
    }

    //To verify clicking on Common Operations navigates to Operations page
    public String clickOnCommonfromTopBar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("commonontop")))).click();
        String commonURL = driver.getCurrentUrl();
        reloadonIconClick();
        return commonURL;
    }

    //To verify the content of second top row
    public String verifySecondTopRow() {
        WebElement secondTopRowLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("secondTopRowLabel"))));
        if (secondTopRowLabel.isDisplayed())
            return secondTopRowLabel.getText();
        else return secondTopRowLabel.getTagName();
    }

    //To verify the copyright information
    public String copyrightTest() throws InterruptedException {
        return driver.findElement(By.xpath(props.getProperty("copyright"))).getText();
    }


    //To verify Drishti Mat Card Information, contents and functionalities on Home Page
    public String verifyDrishtiMatCard() throws InterruptedException {
        Thread.sleep(1000);
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("drishtiicon")))).isDisplayed()) {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("drishtiHeader")))).getText().contains("Drishti")) {
                List<WebElement> reportlabel = driver.findElements(By.className("dashboard-card-text"));
                for (WebElement reportlabelarray : reportlabel) {
                    if (reportlabelarray.getText().contains("Pending Reports") && reportlabelarray.getText().contains("Approved Reports") && reportlabelarray.getText().contains("Rejected Reports")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("viewListDrishti")))).click();
                    }
                }
            }
        }
        return driver.getCurrentUrl();
    }

    //To verify Shonit Mat Card Information, contents and functionalities on Home Page
    public String verifyShonitMatCard() {
        reloadonIconClick();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("shoniticon")))).isDisplayed()) {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("shonitHeader")))).getText().contains("Shonit")) {
                List<WebElement> reportlabel = driver.findElements(By.className("dashboard-card-text"));
                for (WebElement reportlabelarray : reportlabel) {
                    if (reportlabelarray.getText().contains("Pending Reports") && reportlabelarray.getText().contains("Approved Reports") && reportlabelarray.getText().contains("Rejected Reports")) {
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("viewListShonit")))).click();
                    }
                }
            }
        }
        return driver.getCurrentUrl();
    }

    //To verify shrava Mat Card Information, contents and functionalities on Home Page
    public String verifyShravaMatCard() {
        reloadonIconClick();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("shravaicon")))).isDisplayed()) {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("shravaHeader")))).getText().contains("shrava")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("viewListShrava")))).click();
            }
        }
        return driver.getCurrentUrl();
    }

    //To verify shrava Mat Card Information, contents and functionalities on Home Page
    public String verifyCXRMatCard() {
        reloadonIconClick();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("cxrHeader")))).getText().contains("CXR")) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("viewListcxr")))).click();
        }
        return driver.getCurrentUrl();
    }

    //To verify Aadi Mat Card Information, contents and functionalities on Home Page
    public String verifyAadiMatCard() {
        reloadonIconClick();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(props.getProperty("aadiicon")))).isDisplayed()) {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("aadiHeader")))).getText().contains("Aadi")) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("viewListAadi")))).click();
            }
        }
        return driver.getCurrentUrl();
    }

    //To verify if the report page opens with accurate number of pending reports as shown in the matcard
    public boolean pendingreportRangeVerification() throws InterruptedException {
        WebElement pendingReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("pendingReport"))));
        String numPend = pendingReport.getText();
        pendingReport.click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("paginationRange")))).getText().contains(numPend)) {
            return true;
        } else return false;
    }

    //To verify if the report page opens with accurate number of approved reports as shown in the matcard
    public boolean approvedreportRangeVerification() {
        WebElement approvedReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approvedReport"))));
        String numPend = approvedReport.getText();
        approvedReport.click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("paginationRange")))).getText().contains(numPend)) {
            return true;
        } else return false;
    }

    //To verify if the report page opens with accurate number of rejected reports as shown in the matcard
    public boolean rejectedreportRangeVerification() {
        WebElement rejectedReport = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rejectedReport"))));
        String numPend = rejectedReport.getText();
        rejectedReport.click();
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("paginationRange")))).getText().contains(numPend)) {
            return true;
        } else return false;
    }
}

