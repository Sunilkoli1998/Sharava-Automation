package com.Shrava;

import com.Shrava_sanity.ListReport;
import com.Shrava_sanity.MandaraHome;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ListReportTest extends BrowserSetUp {

    public Properties props;
    public ListReport listreport;
    private final Logger logger = LogManager.getLogger(ListReportTest.class);
    public BrowserSetUp pageload;
    boolean productcard;
    public MandaraHome mandaraHome;

    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        mandaraHome = new MandaraHome(driver);
        listreport = new ListReport(driver);
        props = PropertiesFile.prop;
        PropertiesFile.readShonitListReportFile();
        PropertiesFile.readPropertiesFIle();
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readShonitPropertiesFile();
        productcard = false;
    }


    //Click on Shonit List Report
    @Parameters({"product"})
    @Test(priority = 2)
    public void clickonListReportTest(String product) throws InterruptedException {
        //Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        test = extent.createTest("List Report Test");
        Assert.assertTrue(listreport.clickonListReport(product));
        logger.info("Clicked on List Report Successfully to load");
    }


    //Verify the List Report Page loaded
    @Test(priority = 3)
    public void visibilityOfVersionCard() throws InterruptedException {
        if (listreport.verifyVersionCard()) {
            Assert.assertTrue(true);
            logger.info("about app is opened");
            productcard = true;
        }
    }

    //Verify the List Report Page loaded
    @Test(priority = 5)
    public void visibilityOfPlateformVersion() throws InterruptedException {
        if (productcard) {
            Assert.assertTrue(listreport.verifyPlateformVersion().contains(props.getProperty("plateformversiontext")));
            logger.info("plateform version is verified");
        }
    }

    @Test(priority = 15)
    public void visibilityOfHelpIcon() {
        if (productcard) {
            Assert.assertTrue(listreport.verifyHelpButton().contains(props.getProperty("helpbuttontext")));
            logger.info("help icon is verified");
        }

    }

    @Test(priority = 17, enabled = true)
    public void VerifyClickOnNotificationIcon() throws InterruptedException {
        Assert.assertTrue(listreport.verifyNotificationIcon("Shonit"));
        logger.info("Clicked on notification icon Successfully ");
    }


    //Verify the List Report Page loaded
    @Test(priority = 7)
    public void visibilityOfProductName() throws InterruptedException {
        if (productcard) {
            Assert.assertTrue(listreport.verifyProductName().contains(props.getProperty("productnametext")));
            logger.info("product name is verified");
        }
    }

    //Verify the List Report Page loaded
    @Test(priority = 9)
    public void visibilityOfSoftwareVersion() throws InterruptedException {
        if (productcard) {
            Assert.assertTrue(listreport.verifySoftwareVersion().contains(props.getProperty("softwareversiontext")));
            logger.info("software version is verified");
        }
    }

    //Verify the List Report Page loaded
    @Test(priority = 11)
    public void closeproductVersionCard() throws InterruptedException {
        if (productcard) {
            Assert.assertTrue(listreport.closeproductVersionCard());
            logger.info("about app is closed");
        }
    }

    //Verify the List Report Page loaded
    @Test(priority = 13)
    public void verifyListReportpage() throws InterruptedException {
        //Thread.sleep(2000);
        props = PropertiesFile.prop;
        String verifyListReportpage = listreport.verifyListReportPage();
        Assert.assertEquals(verifyListReportpage, "Analysis Reports");
        logger.info("List Report page loaded");
    }



//-------------------------- Testing part -------------------------


    //Verify the List Report Page loaded
    @Parameters({"product"})
    @Test(priority = 101, enabled = true)
    public void verifymodulname(String product) throws InterruptedException {
        //Thread.sleep(2000);
        props = PropertiesFile.prop;
        Assert.assertTrue(listreport.checkmodulname().contains("Shrava"));
        logger.info("Modul name contains :" + "Shrava");
    }

    //Verify the List Report Page loaded
    @Test(priority = 101, enabled = true)
    public void verifyNotpadIcon() throws InterruptedException {
        Assert.assertTrue(listreport.checkNotepadIcon());
        logger.info("Notepad Icon is available before 'Analysis Reports' text");
    }

    //Verify Search field is shown
    @Test(priority = 103, enabled = false)
    public void verifysearchField() throws InterruptedException {
        Assert.assertTrue(listreport.searchfield());
        logger.info("Search field is Displayed");
    }

    //verify the filter icon id displayed
    @Test(priority = 105, enabled = true)
    public void verifyfilterIcon() throws InterruptedException {
        Assert.assertTrue(listreport.filtericon());
        logger.info("Filter icon is displayed");
    }

    //Verify the Refresh link icon and click on it
    @Test(priority = 107, enabled = true)
    public void verifyrefreshicon() throws InterruptedException {
        Assert.assertTrue(listreport.refreshicon());
        logger.info("Refresh icon is displayed");
    }

    @Test(priority = 108, enabled = true)
    public void verifyOptionFromUserIconOnShonitReportPage() throws InterruptedException {
        String user = props.getProperty("username3");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        //	String options="Hi! "+user+" "+props.getProperty("usericonoptions_text");
        Assert.assertEquals(mandaraHome.verifyOptionFromUserIcon(), "assignment_ind\n"
                + "My Account\n"
                + "keyboard_tab\n"
                + "Logout ");
        logger.info("All expected option are available after click on user icon");
    }

    @Test(priority = 110, enabled = true)
    public void termsNCondTestOnShonitReportPage() throws InterruptedException {
        Assert.assertTrue(mandaraHome.termsNCondTest());
        logger.info("Terms of Service hyperlink is displayed");
        logger.info("Terms of Service dialog box opened up on clicking on hyperlink");
        logger.info("The header of the dialog box is 'Terms of Service'");
        logger.info("Terms of Service dialog box is closed successfully");
    }

    //verify the table headers of List Report page

    @Parameters({"product"})
    @Test(priority = 109, enabled = true)
    public void verifytableheader(String product) throws InterruptedException {
        String VerifyList = listreport.getListReportTableheader();
        Assert.assertEquals(VerifyList, "PRIORITY,SAMPLE ID,DESCRIPTION,INSTALLATION,SUBMITTED BY,SUBMITTED AT,REVIEWER,STATUS,");
        logger.info("Success: validation of list of columns in Shonit list Report " + VerifyList);
    }

    //verify the table headers of List Report page
    @Test(priority = 111, enabled = true)
    public void checkpaginationeditbox() throws InterruptedException {
        Assert.assertTrue(listreport.checkpaginationeditbox());
        logger.info("edit box is visible to enter page number ");
    }

    //verify the table headers of List Report page
    @Test(priority = 115, enabled = true)
    public void checkpaginationposition() throws InterruptedException {
        Assert.assertTrue(listreport.checkpaginationposition());
        logger.info("table page numbers are visible");
    }

    //verify the table headers of List Report page
    @Test(priority = 113, enabled = true)
    public void checkpaginationchevronicons() throws InterruptedException {
        Assert.assertTrue(listreport.checkpaginationchevronicons());
        logger.info("chevron icons are visible at the bottom of page");
    }



    @Parameters({"product"})
    @Test(priority = 225, enabled = true)
    public void verifySearchEnterText(String product) throws InterruptedException {
        Assert.assertTrue(listreport.searchSampelID("Shonit"));
        logger.info("Search Text passed successfully");
    }

    @Test(priority = 226, enabled = false)
    public void verifyAddAssignee() throws InterruptedException {
        Assert.assertTrue(listreport.addAssigneeOnListReport());
        logger.info("reviewer is assigned for verify the report");
    }

    
    @Test(priority = 227,enabled=false)
    public void verifySearchdata() throws InterruptedException {
        Assert.assertTrue(listreport.verifySearchTabledata());
        logger.info("Table sample ID and des is being shown according to searched text");
    }
    

}
