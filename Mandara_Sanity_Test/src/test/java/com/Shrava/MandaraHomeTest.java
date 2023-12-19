package com.Shrava;

import com.Shrava_sanity.MandaraHome;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.Set;


public class MandaraHomeTest extends BrowserSetUp {
    public Properties props;
    public MandaraHome mandaraHome;
    String sideproductnames = "";
    private static final Logger logger = LogManager.getLogger(MandaraHomeTest.class);

    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        mandaraHome = new MandaraHome(driver);
        props = PropertiesFile.prop;
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readPropertiesFIle();
    }

    //To verify the successful load of Mandara Home Page
    @Test(priority = 3, enabled = true)
    public void checkMadaraHomePage() throws InterruptedException {
        test = extent.createTest("Mandara Home Test");
        Assert.assertTrue(mandaraHome.checkMadaraHomePage());
        logger.info("Mandara Home Page has loaded successfully");
    }

    //To verify the SigTuple mini icon's properties on top bar
    @Test(priority = 5, enabled = true, dependsOnMethods = "checkMadaraHomePage")
    public void checkSigtupleIcon() throws InterruptedException {
        Assert.assertTrue(mandaraHome.checkSigtupleIcon().contains("SigTuple"));
        logger.info("Sigtuple mini icon on static bar is present on top left and is clickable");
    }

    @Test(priority = 7, enabled = false, dependsOnMethods = "checkMadaraHomePage")
    public void checkSideMenu() throws InterruptedException {
        Assert.assertTrue(mandaraHome.checkSideMenu());
        logger.info("Side Menu on static bar is present on top left and is clickable");
    }

    @Test(priority = 9, enabled = true, dependsOnMethods = "checkMadaraHomePage")
    public void checkusericon() throws InterruptedException {
        Assert.assertTrue(mandaraHome.checkusericon());
        logger.info("User Icon is present on static bar is present on top right and is clickable");
    }

    @Test(priority = 11, enabled = true, dependsOnMethods = "checkusericon")
    public void verifyOptionFromUserIcon() throws InterruptedException {
        String user = props.getProperty("username3");
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        //	String options="Hi! "+user+" "+props.getProperty("usericonoptions_text");
        Assert.assertEquals(mandaraHome.verifyOptionFromUserIcon(), "assignment_ind\n"
                + "My Account\n"
                + "keyboard_tab\n"
                + "Logout ");
        logger.info("All expected option are available after click on user icon");
    }

    //To get the number of Products currently present on the top bar
    @Test(priority = 13, enabled = true, dependsOnMethods = "checkMadaraHomePage")
    public void numberOfProductsForUser() throws InterruptedException {
        int count = mandaraHome.numberOfProductsForUser();
        Assert.assertTrue(count > 0);
        logger.info("Number of Products currently accessible to users is " + String.valueOf(count));
    }


    //To verify copy right information
    @Test(priority = 27, enabled = false, dependsOnMethods = "checkMadaraHomePage")
    public void copyrightTest() throws InterruptedException {
        if (mandaraHome.copyrightTest().contains(props.getProperty("copyrightText"))) {
            logger.info("Copyright information verified successfully");
        } else
            logger.info("Copyright info is not identified");
    }

    //To verify Terms of Service contents and displaying the success result
    @Test(priority = 29, enabled = false, dependsOnMethods = "checkMadaraHomePage")
    public void termsNCondTest() throws InterruptedException {
        Assert.assertTrue(mandaraHome.termsNCondTest());
        logger.info("Terms of Service hyperlink is displayed");
        logger.info("Terms of Service dialog box opened up on clicking on hyperlink");
        logger.info("The header of the dialog box is 'Terms of Service'");
        logger.info("Terms of Service dialog box is closed successfully");
    }

    //To verify Privacy Policy functionality and switching back to original window
    @Test(priority = 31, enabled = false, dependsOnMethods = "checkMadaraHomePage")
    public void privacyPolicy() throws InterruptedException {
        if (mandaraHome.privacyPolicy()) {
            logger.info("Privacy policy window opened on the text browser tab");
            Set<String> windowhandlers = driver.getWindowHandles();
            for (String s : windowhandlers) {
                driver.switchTo().window(s);
                break;
            }
            logger.info("The Privacy policy document is closed");
            // Thread.sleep(2000);
        } else
            logger.info("The Privacy policy document is not verified");
    }


//------------------------------- verify availability of all products ---------------------------------

    /*@Test(priority =115, enabled=true,dependsOnMethods="checkMadaraHomePage")
    public void availabilityOfAadiCard() throws InterruptedException
    {
    	Thread.sleep(5000);
    	if(mandaraHome.getProductnames().contains("Aadi")) {
    		Assert.assertTrue(true);
    		logger.info("Aadi product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Aadi product is not available for current user");
    	}
    }

    @Test(priority =117, enabled=true,dependsOnMethods="checkMadaraHomePage")
    public void availabilityOfDrishtiFundusCard() throws InterruptedException
    {
    	if(mandaraHome.getProductnames().contains("Fundus")) {
    		Assert.assertTrue(true);
    		logger.info("Drishti Fundus product is available for current user");
    	}
    	else{z
    		Assert.assertTrue(false);
            logger.info("Drishti Fundus product is not available for current user");
    	}
    }

    @Test(priority =119, enabled=true,dependsOnMethods="checkMadaraHomePage")
    public void availabilityOfDrishtiOCTCard() throws InterruptedException
    {
    	if(mandaraHome.getProductnames().contains("OCT")) {
    		Assert.assertTrue(true);
    		logger.info("Drishti OCT product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Drishti OCT product is not available for current user");
    	}
    }
    */
    @Test(priority = 121, enabled = true, dependsOnMethods = "checkMadaraHomePage")
    public void availabilityOfShravaCard() throws InterruptedException {
        if (mandaraHome.getProductnames().contains("Shrava")) {
            Assert.assertTrue(true);
            logger.info("Shrava product is available for current user");
        } else {
            Assert.assertTrue(false);
            logger.info("Shrava product is not available for current user");
        }
    }

   /* @Test(priority =123, enabled=true,dependsOnMethods="checkMadaraHomePage")
    public void availabilityOfShravaCard() throws InterruptedException
    {
    	if(mandaraHome.getProductnames().contains("Shrava")) {
    		Assert.assertTrue(true);
    		logger.info("Shrava product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Shrava product is not available for current user");
    	}
    }

    @Test(priority =125, enabled=true,dependsOnMethods="checkMadaraHomePage")
    public void availabilityOfDigitizerCard() throws InterruptedException
    {
    	if(mandaraHome.getProductnames().contains("Digitizer")) {
    		Assert.assertTrue(true);
    		logger.info("Digitizer product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Digitizer product is not available for current user");
    	}
    }
    */
//------------------------------- verify card details ---------------------------------


    //To verify on clicking Shonit on top bar navigated to it's List/Analysis Report
    @Test(priority = 235, enabled = true)
    public void verifyShonitCarddetails() throws InterruptedException {
        String productpath = props.getProperty("Shrava_Product");
        if (mandaraHome.verifyCardDetails(productpath)) {
            Assert.assertTrue(true);
            logger.info("All details on shonit card is verified ");
        } else {
            Assert.assertTrue(false);
            logger.info("All details on shonit card is not same as expected ");
        }

    }
/*
    //To verify on clicking shrava on top bar navigated to it's List/Analysis Report
    @Test(priority = 237, enabled=true,dependsOnMethods="availabilityOfShravaCard")
    public void verifyShravaCarddetails() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"Shrava"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyCardDetails(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All details on shrava card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All details on shrava card is not same as expected ");
    	}
    }

    //To verify on clicking Drishti on top bar navigated to it's List/Analysis Report
    @Test(priority = 239, enabled=true,dependsOnMethods="availabilityOfDrishtiFundusCard")
    public void verifyDrishtiFundusCarddetails() throws InterruptedException
     {
    	String productpath=props.getProperty("productpath_first")+"Fundus"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyCardDetails(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All details on Fundus card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All details on Fundus card is not same as expected ");
    	}
    }

    //To verify on clicking Aadi on top bar navigated to it's List/Analysis Report
    @Test(priority = 241, enabled=true,dependsOnMethods="availabilityOfDrishtiOCTCard")
    public void verifyDrishtiOCTCarddetails() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"OCT"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyCardDetails(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All details on OCT card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All details on OCT card is not same as expected ");
    	}
    }

    //To verify on clicking CXR on top bar navigated to it's List/Analysis Report
    @Test(priority = 243, enabled=true,dependsOnMethods="availabilityOfAadiCard")
    public void verifyAadiCarddetails() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"Aadi"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyCardDetails(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All details on shonit card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All details on shonit card is not same as expected ");
    	}
    }

    @Test(priority = 245, enabled=true,dependsOnMethods="availabilityOfDigitizerCard")
    public void verifyDigitizerCarddetails() throws InterruptedException
     {
    	String productpath=props.getProperty("productpath_first")+"Digitizer"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyCardDetails(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All details on Digitizer card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All details on Digitizer card is not same as expected ");
    	}
    }
*/
//------------------------------- verify number of report links ---------------------------------

    //To verify on clicking Shonit on top bar navigated to it's List/Analysis Report
    @Test(priority = 347, enabled = false, dependsOnMethods = "availabilityOfShonitCard")
    public void verifyShonitReportCount() throws InterruptedException {
        String productpath = props.getProperty("Shonit_Product");
        if (mandaraHome.verifyReportCount(productpath)) {
            Assert.assertTrue(true);
            logger.info("All Report Count on shonit card is verified ");
        } else {
            Assert.assertTrue(false);
            logger.info("All Report Count on shonit card is not same as expected ");
        }

    }
/*
    //To verify on clicking shrava on top bar navigated to it's List/Analysis Report
    @Test(priority = 349, enabled=true,dependsOnMethods="availabilityOfShravaCard")
    public void verifyShravaReportCount() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"Shrava"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyReportCount(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All Report Count on shrava card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All Report Count on shrava card is not same as expected ");
    	}
    }

    //To verify on clicking Drishti on top bar navigated to it's List/Analysis Report
    @Test(priority = 351, enabled=true,dependsOnMethods="availabilityOfDrishtiFundusCard")
    public void verifyDrishtiFundusReportCount() throws InterruptedException
     {
    	String productpath=props.getProperty("productpath_first")+"Fundus"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyReportCount(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All Report Count on Fundus card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All Report Count on Fundus card is not same as expected ");
    	}
    }

    //To verify on clicking Aadi on top bar navigated to it's List/Analysis Report
    @Test(priority = 353, enabled=true,dependsOnMethods="availabilityOfDrishtiOCTCard")
    public void verifyDrishtiOCTReportCount() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"OCT"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyReportCount(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All Report Count on OCT card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All Report Count on OCT card is not same as expected ");
    	}
    }

    //To verify on clicking CXR on top bar navigated to it's List/Analysis Report
    @Test(priority = 355, enabled=true,dependsOnMethods="availabilityOfAadiCard")
    public void verifyAadiReportCount() throws InterruptedException
    {
    	String productpath=props.getProperty("productpath_first")+"Aadi"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyReportCount(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All Report Count on shonit card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All Report Count on shonit card is not same as expected ");
    	}
    }

    @Test(priority = 357, enabled=true,dependsOnMethods="availabilityOfDigitizerCard")
    public void verifyDigitizerReportCount() throws InterruptedException
     {
    	String productpath=props.getProperty("productpath_first")+"Digitizer"+props.getProperty("productpath_second");
    	if(mandaraHome.verifyReportCount(productpath)) {
    		Assert.assertTrue(true);
            logger.info("All Report Count on Digitizer card is verified ");
    	}
        else {
    		Assert.assertTrue(false);
            logger.info("All Report Count on Digitizer card is not same as expected ");
    	}
    } */

//------------------------------- verify product from side menu ---------------------------------

    @Test(priority = 413, enabled = false, dependsOnMethods = "checkMadaraHomePage")
    public void getSideProductName() throws InterruptedException {
        sideproductnames = mandaraHome.getProductnamesfromsidemenu();
        if (sideproductnames.length() > 0) {
            Assert.assertTrue(true);
            logger.info("list of products are taken from side menu");
        } else {
            Assert.assertTrue(false);
            logger.info("failed in taking list of products from side menu");
        }
    }
    /*
    @Test(priority =415, enabled=true,dependsOnMethods="availabilityOfAadiCard")
    public void verifyAadiFromSideMenu() throws InterruptedException
    {
    	Thread.sleep(5000);
    	if(sideproductnames.contains("Aadi")) {
    		Assert.assertTrue(mandaraHome.selectproduct("Aadi"));
    		logger.info("Aadi product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Aadi product is not available for current user");
    	}
    }

    @Test(priority =417, enabled=true,dependsOnMethods="availabilityOfDrishtiFundusCard")
    public void verifyDrishtiFundusFromSideMenu() throws InterruptedException
    {
    	if(sideproductnames.contains("Fundus")) {
    		Assert.assertTrue(mandaraHome.selectproduct("Fundus"));
    		logger.info("Drishti Fundus product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Drishti Fundus product is not available for current user");
    	}
    }

    @Test(priority =419, enabled=true,dependsOnMethods="availabilityOfDrishtiOCTCard")
    public void verifyDrishtiOCTFromSideMenu() throws InterruptedException
    {
    	if(sideproductnames.contains("OCT")) {
    		Assert.assertTrue(mandaraHome.selectproduct("OCT"));
    		logger.info("Drishti OCT product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Drishti OCT product is not available for current user");
    	}
    } */

    @Test(priority = 421, enabled = false, dependsOnMethods = "availabilityOfShonitCard")
    public void verifyShonitFromSideMenu() throws InterruptedException {
        if (sideproductnames.contains("Shonit")) {
            Assert.assertTrue(mandaraHome.selectproduct("Shonit"));
            logger.info("Shonit product is available for current user");
        } else {
            Assert.assertTrue(false);
            logger.info("Shonit product is not available for current user");
        }
    }
    /*
    @Test(priority =423, enabled=true,dependsOnMethods="availabilityOfShravaCard")
    public void verifyShravaFromSideMenu() throws InterruptedException
    {
    	if(sideproductnames.contains("Shrava")) {
    		Assert.assertTrue(mandaraHome.selectproduct("Shrava"));
    		logger.info("Shrava product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Shrava product is not available for current user");
    	}
    }

    @Test(priority =425, enabled=true,dependsOnMethods="availabilityOfDigitizerCard")
    public void verifyDigitizerFromSideMenu() throws InterruptedException
    {
    	if(sideproductnames.contains("Digitizer")) {
    		Assert.assertTrue(mandaraHome.selectproduct("Digitizer"));
    		logger.info("Digitizer product is available for current user");
    	}
    	else{
    		Assert.assertTrue(false);
            logger.info("Digitizer product is not available for current user");
    	}
    }


/*
    //To verify number of dashboard mat card present on home page and matching if the number of products on bar is same
    @Test(priority = 33, enabled=true)
    public void numberOfproductFromSideMenu() {
         String count = mandaraHome.numberOfproductFromSideMenu();
        if (Integer.valueOf(count)>0) {
            logger.info("All products card corresponding to each available products for user is present on Home Page");
        }
        else
        	logger.info("All products card is not availanle at home page");
        //Assert.assertTrue(count>0);
    }


    @Test(priority = 47, enabled=true)
    public void verifyNevigationtoDashboard() {
    	try {
        if (mandaraHome.verifyNevigationtoDashboard().contains(props.getProperty("shonitcurrentURL")))
            logger.info("Shonit Analysis Report page is loaded successfully on clicking Shonit from Top Bar");
        else
            logger.info("Shonit is not clickable to navigate to Shonit List Reports page");
    	}
    	catch(Exception e) {
            logger.info("Shonit is not clickable to navigate to Shonit List Reports page");
    	}
    }

    //To verify on clicking Common on top bar navigated to user's Operations page
     @Test(priority = 49, enabled=true)
    public void verifyNevigationtoCommonpage() {
    	try {
        if (mandaraHome.verifyNevigationtoCommonpage().contains(props.getProperty("shonitcurrentURL")))
            logger.info("Shonit Analysis Report page is loaded successfully on clicking Shonit from Top Bar");
        else
            logger.info("Shonit is not clickable to navigate to Shonit List Reports page");
    	}
    	catch(Exception e) {
            logger.info("Shonit is not clickable to navigate to Shonit List Reports page");
    	}
    }
*/
}



