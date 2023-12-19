package com.Shrava_sanity;


import com.data.Shrava_data.PropertiesFile;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Login {


    public Properties props;
    public WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Login.class);

    public Login(WebDriver driver) throws Exception {
        this.driver = driver;
        props = PropertiesFile.prop;
        int time = 30;
        wait = new WebDriverWait(driver, time);
        PropertiesFile.readPropertiesFIle();
        PropertiesFile.readMandaraHomePropertiesFile();
    }

    //fetches title of the Login Page
    public String verifySigTupleTittle() {
        return driver.getTitle();
    }

    //Verify if the Mandara UI app is loaded successfully
    public boolean verifyMandaraAppLoad() {
        props = PropertiesFile.prop;
        WebElement mandaraApp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("mandaraapp"))));
        if (mandaraApp != null) {
            logger.info("mandara app is loaded");
            return true;
        } else return false;
    }

    //fetches Signin logo in Login page
    public Boolean visibilityOfSigtupleLogo() {
        props = PropertiesFile.prop;
        WebElement siglogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtuplelogo"))));
        if (siglogo != null && siglogo.getAttribute("alt").contains("SigTuple")) {
            logger.info("sigtuple logo is loaded");
            return true;
        } else return false;
    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean visibilityOfLoginContainer() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("logincontainer"))));
        if (container.isDisplayed()) {
            logger.info("login container is loaded");
            return true;
        } else return false;
    }

    //get login page details
    public String visibilityOfUserNameField() {
        props = PropertiesFile.prop;
        String text = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("uernamefield"))));
        if (field.isDisplayed()) {
            logger.info("user name field is displayed");
            return field.getAttribute("placeholder");
        }
        return text;
    }

    //verify password placeholder
    public String visibilityOfPassNameField() {
        props = PropertiesFile.prop;
        String text = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passfield"))));
        if (field.isDisplayed()) {
            logger.info("user name field is displayed");
            return field.getAttribute("placeholder");
        }
        return text;
    }

    //verify password placeholder
    public String visibilityOfLoginButton() {
        props = PropertiesFile.prop;
        String text = "";
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("signinbtn"))));
        if (button.isDisplayed()) {
            logger.info("Login buttin is visible");
            return button.getText();
        }
        return text;
    }

    //verify password placeholder
    public String enabilityOfLoginButton() {
        props = PropertiesFile.prop;
        String text = "Login buttin is disabled";
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("signinbtn"))));
        if (button.getAttribute("class").contains("disable"))
            logger.info(text);
        else {
            text = "login button is enabled";
            logger.info(text);
        }

        return text;
    }

    //Arrest User Alert messages for username, password and forgot password email fields
    public String verifyUserNameAlerttest() {
        props = PropertiesFile.prop;
        String msg = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("uernamefield"))));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtuplelogo"))));
        if (field.isDisplayed() && logo.isDisplayed()) {
            field.click();
            logo.click();
            msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("usernamefieldAlert")))).getText();
            logger.info("user name alert Msg :" + msg);
        }
        return msg;
    }

    //verify passwork aleart msg
    public String verifyPasswordAlerttest() {
        props = PropertiesFile.prop;
        String msg = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passfield"))));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtuplelogo"))));
        if (field.isDisplayed() && logo.isDisplayed()) {
            field.click();
            logo.click();
            msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passfieldAlert")))).getText();
            logger.info("password field alert Msg :" + msg);
        }
        return msg;
    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean visibilityOfForgotPasswordButton() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("forgotpassbtn"))));
        if (button.isDisplayed()) {
            logger.info("forgot pass button is displayed");
            return true;
        }
        return false;
    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean clickOnForgotPasswordButton() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("forgotpassbtn"))));
        if (button.isDisplayed()) {
            button.click();
            logger.info("clicked on forgot pass button");
            return true;
        }
        return false;
    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean verifyForgotPasswordPageLoaded() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement page = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        if (page.isDisplayed()) {
            logger.info("forgot password page loaded");
            return true;
        }
        return false;
    }

    //Verify user alert messages for username, password and forgot password fields
    public String visibilityOfEmailIDField() throws InterruptedException {
        props = PropertiesFile.prop;
        String text = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        if (field.isDisplayed()) {
            logger.info("Email field is displayed");
            return field.getAttribute("placeholder");
        }
        return text;
    }

    //Verify user alert messages for username, password and forgot password fields
    public String visibilityOfBackToLoginButton() throws InterruptedException {
        String text = "";
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("backtologinbtn"))));
        if (button.isDisplayed()) {
            logger.info("back to login button is displayed");
            return button.getText();
        }
        return text;
    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean visibilityOfReserPasswordButton() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passresetbtn"))));
        if (button.isDisplayed()) {
            logger.info("password reset button is displayed and disabled");
            return true;
        }
        return false;
    }

    //Verify user alert messages for username, password and forgot password fields
    public String enabilityOfReserPasswordButton() throws InterruptedException {
        props = PropertiesFile.prop;
        String text = "reset buttin is disabled";
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("passresetbtn"))));
        if (button.getAttribute("class").contains("disable"))
            logger.info(text);
        else {
            text = "reset button is enabled";
            logger.info(text);
        }

        return text;
    }

    //
    public String verifyEmailFieldAlertTest() throws InterruptedException {
        props = PropertiesFile.prop;
        String msg = "";
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtuplelogo"))));
        if (field.isDisplayed() && logo.isDisplayed()) {
            field.click();
            logo.click();
            msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfieldAlert")))).getText();
            logger.info("Email field alert Msg :" + msg);
        }
        return msg;
    }

    //
    public String verifyAlertforNotAnEmailid() throws InterruptedException {
        props = PropertiesFile.prop;
        String msg = "";
        WebElement emailfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        emailfield.click();
        emailfield.clear();
        emailfield.sendKeys("abcdefgh");
      //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);

        //new WebDriverWait(driver,40);
        //Thread.sleep(1000);
        WebElement resetbutton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passresetbtn"))));
       // resetbutton.click();
        if (resetbutton.isDisplayed() && resetbutton.getAttribute("class").contains("disable")) {
            logger.info("entered text is not an email ID");
            msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfieldAlert")))).getText();
            logger.info("Email field alert Msg :" + msg);
        }
        return msg;
    }

    //
    public String verifyAlertforInvalideEmail() throws InterruptedException {
        props = PropertiesFile.prop;
        String alert = "";
        WebElement emailfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        emailfield.click();
        emailfield.clear();
        emailfield.sendKeys("abc@abc.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
        // new WebDriverWait(driver,30);
        //Thread.sleep(1000);
        WebElement resetbutton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passresetbtn"))));
        if (resetbutton.isDisplayed() && !resetbutton.getAttribute("class").contains("disable")) {
            logger.info("entered text is an email ID");
            resetbutton.click();
            try {
                alert = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("alertmsg")))).getText();
            } catch (Exception e) {
                logger.info("no any popup for confirmation");
            }
        }
        return alert;
    }

    //
    public String verifyAlertforValideEmail() throws InterruptedException {
        props = PropertiesFile.prop;
        String alert = "";
        WebElement emailfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("emailfield"))));
        emailfield.click();
        emailfield.clear();
        emailfield.sendKeys(props.getProperty("resetemail"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // new WebDriverWait(driver,40);
        //Thread.sleep(2000);
        WebElement resetbutton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passresetbtn"))));
        if (resetbutton.isDisplayed() && !resetbutton.getAttribute("class").contains("disable")) {
            logger.info("entered text is an email ID");
            resetbutton.click();
            try {
                alert = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("alertmsg")))).getText();
            } catch (Exception e) {
                logger.info("no any popup for confirmation" + alert);
            }
        }
        System.out.println("alert: " + alert);
        return alert;
    }

    //
    public boolean clickonBackToLogin() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("backtologinbtn"))));
        if (button.isDisplayed()) {
            button.click();
            logger.info("back to login button clicked");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
            // new WebDriverWait(driver,20);
            //Thread.sleep(1000);
            driver.navigate().refresh();
            return true;
        }
        return false;
    }

    //
    public String verifyCopyrightOnLoginPage() throws InterruptedException {
        props = PropertiesFile.prop;
        String msg = "";
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("copyrightOnLogin"))));
        if (text.isDisplayed()) {
            msg = text.getText();
            logger.info("copyright text : " + msg);
        }
        return msg;
    }

    //Pass username and Password to Login Test class to test valid and invalid testcase
    public String invailidlogin(String uname, String pwd) throws InterruptedException {

        props = PropertiesFile.prop;
        String alert = "";
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("uernamefield"))));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passfield"))));

        username.click();
        username.clear();
        username.sendKeys(uname);
        password.click();
        password.clear();
        password.sendKeys(pwd);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //  new WebDriverWait(driver,40);
        //Thread.sleep(2000);
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("signinbtn"))));

        if (button.isDisplayed() && !button.getAttribute("class").contains("disable"))
            button.click();
        try {
            alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("alertmsg")))).getText();
            driver.findElement(By.xpath(props.getProperty("CloseAlert"))).click();
        } catch (Exception e) {
            logger.info("no any popup for confirmation");
        }

        return alert;
    }

    //Pass username and Password to Login Test class to test valid and invalid testcase
    public boolean vailidlogin(String uname, String pwd) throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("uernamefield"))));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("passfield"))));

        username.click();
        username.clear();
        username.sendKeys(uname);
        password.click();
        password.clear();
        password.sendKeys(pwd);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        // new WebDriverWait(driver,40);
        // Thread.sleep(2000);
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("signinbtn"))));

        if (button.isDisplayed() && !button.getAttribute("class").contains("disable")) {
            button.click();
            logger.info("clicked on login button");
            return true;
        }
        return false;

    }

    //Verify user alert messages for username, password and forgot password fields
    public boolean visibilityMandaraHomePage() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement page = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("mandarahomepage"))));
        if (page.isDisplayed()) {
            logger.info("Mandara home page is loaded");
            return true;
        }
        return false;
    }


}


