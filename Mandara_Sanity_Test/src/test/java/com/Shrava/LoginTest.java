package com.Shrava;

import com.Shrava_sanity.Login;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginTest extends BrowserSetUp {
    public WebDriver driver;
    public static Properties props;
    public Login login;


    private static final Logger logger = LogManager.getLogger(LoginTest.class);


    //This will initialise the driver and create login class object
    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        login = new Login(driver);
        props = PropertiesFile.prop;
        PropertiesFile.readPropertiesFIle();
    }

    //Verify Title
    @Parameters({"url"})
    @Test(priority = 3)
    public void verifySigTupleTittle(String url) {
        test = extent.createTest("Login Test");
       driver.navigate().to("https://qa.sigtuple.com/login");

        Assert.assertEquals(login.verifySigTupleTittle(), "SigTuple Mandāra");
        logger.info("Sigtuple title is successfully verified");
    }

    @Test(priority = 5, enabled = true)
    public void verifyMandaraAppLoad() throws InterruptedException {
        //WebDriverWait wait = new WebDriverWait(driver,10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("sigtuplelogo")));
        // Thread.sleep(1000);
        Assert.assertTrue(login.verifyMandaraAppLoad());
        logger.info("Mandara App is loaded successfully in UI");
    }

    //Verify Signin Logo
    @Test(priority = 7, enabled = true)
    public void visibilityOfSigtupleLogo() throws InterruptedException {
        Assert.assertTrue(login.visibilityOfSigtupleLogo());
        logger.info("Sigtuple icon visible");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 9, enabled = true)
    public void visibilityOfLoginContainer() throws InterruptedException {
        Assert.assertTrue(login.visibilityOfLoginContainer());
        logger.info("Login container is visible");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 11, enabled = true)
    public void visibilityOfUserNameField() throws InterruptedException {
        Assert.assertEquals(login.visibilityOfUserNameField(), "User Name");
        logger.info("verifyVisibilityOfUserNameField");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 13, enabled = true)
    public void visibilityOfPassNameField() throws InterruptedException {
        Assert.assertEquals(login.visibilityOfPassNameField(), "Password");
        logger.info("verifyVisibilityOfPassNameField");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 15, enabled = true)
    public void visibilityOfLoginButton() throws InterruptedException {
        Assert.assertEquals(login.visibilityOfLoginButton(), "Login");
        logger.info("visibilityOfLoginButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 16, enabled = true)
    public void enabilityOfLoginButton() throws InterruptedException {
        Assert.assertEquals(login.enabilityOfLoginButton(), "Login buttin is disabled");
        logger.info("enabilityOfLoginButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 17, enabled = true)
    public void verifyUserNameAlerttest() throws InterruptedException {
        Assert.assertEquals(login.verifyUserNameAlerttest(), "Please enter your user name");
        logger.info("Alert message for Username field verified");
    }

    @Test(priority = 19, enabled = true)
    public void verifyPasswordAlerttest() throws InterruptedException {
        //Thread.sleep(1000);
        Assert.assertEquals(login.verifyPasswordAlerttest(), "Please enter your password");
        logger.info("Alert message for Password field verified");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 21, enabled = true)
    public void visibilityOfForgotPasswordButton() throws InterruptedException {
        // Thread.sleep(1000);
        Assert.assertTrue(login.visibilityOfForgotPasswordButton());
        logger.info("visibilityOfForgotPasswordButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 23, enabled = true)
    public void clickOnForgotPasswordButton() throws InterruptedException {
        //Thread.sleep(1000);
        Assert.assertTrue(login.clickOnForgotPasswordButton());
        logger.info("clickOnForgotPasswordButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 25, enabled = true)
    public void verifyForgotPasswordPageLoaded() throws InterruptedException {
        //Thread.sleep(1000);
        Assert.assertTrue(login.verifyForgotPasswordPageLoaded());
        logger.info("verifyForgotPasswordPageLoaded");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 27, enabled = true)
    public void visibilityOfEmailIDField() throws InterruptedException {
        Assert.assertEquals(login.visibilityOfEmailIDField(), "Email ID");
        logger.info("visibilityOfForgotPasswordButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 29, enabled = true)
    public void visibilityOfBackToLoginButton() throws InterruptedException {
        Assert.assertEquals(login.visibilityOfBackToLoginButton(), "Back to Login");
        logger.info("visibilityOfBackToLoginButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 31, enabled = true)
    public void visibilityOfReserPasswordButton() throws InterruptedException {
        Assert.assertTrue(login.visibilityOfReserPasswordButton());
        logger.info("visibilityOfReserPasswordButton");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 32, enabled = true)
    public void enabilityOfReserPasswordButton() throws InterruptedException {
        Assert.assertEquals(login.enabilityOfReserPasswordButton(), "reset buttin is disabled");
        logger.info("enabilityOfReserPasswordButton");
    }

    @Test(priority = 33, enabled = false)
    public void verifyEmailFieldAlertTest() throws InterruptedException {
        // Thread.sleep(1000);
        Assert.assertEquals(login.verifyEmailFieldAlertTest(), "Please enter valid email id");
        logger.info("Alert message for Email field verified");
    }

    @Test(priority = 34, enabled = false)
    public void verifyAlertforNotAnEmailid() throws InterruptedException {
        //Thread.sleep(1000);
        Assert.assertEquals(login.verifyAlertforNotAnEmailid(), "Please enter valid email id");
        logger.info("verifyAlertforInvalideEmail");
    }

    @Test(priority = 35, enabled = true)
    public void verifyAlertforInvalideEmail() throws InterruptedException {
        // Thread.sleep(1000);
        // Assert.assertTrue(login.verifyAlertforInvalideEmail().contains("No account with this email address exists."));
        Assert.assertEquals(login.verifyAlertforInvalideEmail(), "No account with this email address exists.");
        logger.info("verifyAlertforInvalideEmail");
    }

    @Test(priority = 37, enabled = true)
    public void verifyAlertforValideEmail() throws InterruptedException {
        Thread.sleep(5000);
        // driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        Assert.assertTrue(login.verifyAlertforValideEmail().contains("An e-mail has been sent to "+props.getProperty("resetemail")+" with further instructions."));
        logger.info("verifyAlertforValideEmail");
    }

    @Test(priority = 39, enabled = true)
    public void clickonBackToLogin() throws InterruptedException {
        // Thread.sleep(2000);
        Assert.assertTrue(login.clickonBackToLogin());
        logger.info("On clicking 'Back to Login' button, the user navigates to the login form window");
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 40, enabled = true)
    public void visibilityOfLoginContainer1() throws InterruptedException {
        // Thread.sleep(1000);
        Assert.assertTrue(login.visibilityOfLoginContainer());
        logger.info("Login container is visible");
    }


    @Test(priority = 41, enabled = false)
    public void verifyCopyrightOnLoginPage() throws InterruptedException {
        // Thread.sleep(1000);
        String copyright = login.verifyCopyrightOnLoginPage();
        boolean status = false;
        if (copyright.contains("Copyright")) {
            status = true;
            logger.info("Copy right info is displayed to users on Login Page " + copyright);
        }
        Assert.assertTrue(status);
    }

    //Send Invalid login Credentials
   /* @DataProvider(name = "InvalidAuthentication")
    public static Object[][] Invalidcredentials()  {
        props = PropertiesFile.prop;
        // The number of times data is repeated, test will be executed the same no. of times
        // Here it will execute 2 times

        String user1 = props.getProperty("username1");
       String pwd1 = props.getProperty("password1");

        String user2 = props.getProperty("username2");
        String pwd2 = props.getProperty("password2");

        return new Object[][]{{user1, pwd1},{user2, pwd2}};
    }*/

    //Send Valid Credentials
    @DataProvider(name = "ValidAuthentication")
    public static Object[][] Validcredentials() {
        props = PropertiesFile.prop;
        // The number of times data is repeated, test will be executed the same no. of times
        // Here it will execute 1 times
        String user3 = props.getProperty("username3");
        String pwd3 = props.getProperty("password3");

        return new Object[][]{{user3, pwd3}};
    }

    //Verify Invalid Credentials
   /* @Test(dataProvider = "InvalidAuthentication", priority = 43,enabled=true)
    public void verifyInvalidLogin(String uname, String pwd) throws InterruptedException {
        //Thread.sleep(1000);
        String message = login.invailidlogin(uname, pwd);
        if(message.contains("Invalid login credentials")){
            Assert.assertTrue(true);
            logger.info("Invalid Password/Incorrect username is entered");
        }
    }*/

    //Verfiy Valid Credentials
    @Test(dataProvider = "ValidAuthentication", priority = 45, enabled = true)
    public void verifyValidLogin(String uname, String pwd) throws InterruptedException {
        if (login.vailidlogin(uname, pwd)) {
            Assert.assertTrue(true);
            logger.info("login Done successfully");
        } else {
            logger.info("Login failed");
            Assert.assertTrue(false);
        }
    }

    //Verify user alert messages for username, password and forgot password fields
    @Test(priority = 47, enabled = true)
    public void visibilityMandaraHomePage() throws InterruptedException {
        //Thread.sleep(2000);
        Assert.assertTrue(login.visibilityMandaraHomePage());
        logger.info("Mandara home page is Loaded");
    }


}
