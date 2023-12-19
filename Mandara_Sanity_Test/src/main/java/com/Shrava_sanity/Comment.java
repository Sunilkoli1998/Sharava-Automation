package com.Shrava_sanity;


import com.data.Shrava_data.PropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import java.util.Properties;


public class Comment extends ReportPage {

    public Properties props;
    public WebDriver driver;
    private final WebDriverWait wait;
    private final Logger logger = LogManager.getLogger(Comment.class);

    Map<String, ArrayList<String>> commentRecord = new HashMap<String, ArrayList<String>>();
    ArrayList<String> enteredcomment = new ArrayList<String>();

    public Comment(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        PropertiesFile.readCommentPropertiesFile();
        props = PropertiesFile.prop;
        wait = new WebDriverWait(driver, 30);

    }

    //------------------------------------------- common testing for all tabs ------------------------------------
    //click on comment icon to open the comment window
    public boolean clickoncommentIcon() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        //driver.navigate().refresh();
        Thread.sleep(3000);
        WebElement cm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("commenticon"))));
        if (cm.isDisplayed()) {
            Thread.sleep(3000);
            cm.click();
            flag = true;
        }
        return flag;
    }

    //verify the fuctionality of comment icon
    public void clickonCommentIcon() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(1000);
        WebElement cm = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(props.getProperty("commenticon1"))));
        cm.click();
    }

    //click on comment icon to open the comment window
    public boolean closecommentbox() throws InterruptedException {
        props = PropertiesFile.prop;
        boolean flag = false;
        WebElement cm = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(props.getProperty("closebtn"))));
        if (cm.isDisplayed()) {
            cm.click();
            flag = true;
        }
        Thread.sleep(3000);
        return flag;
    }

    //verify after click on comment icon , comment box is opened properly
    public boolean commentboxlaod() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(1000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(props.getProperty("commentbox")))).isDisplayed();
    }

    //verify comment box header showing at the top of the box
    public String validatecommentbox() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        String comment = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(props.getProperty("commentheader")))).getText();

        return comment;
    }

    //verify user is able to upddate the comment or not(use is assigned or not)
    public boolean checkuser() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(2000);
        return (driver.findElements(By.xpath(props.getProperty("updatebtn"))).size() != 0);
    }

    //verify post button should be clickable
    public boolean checkpostbtnclickable() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(2000);
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(props.getProperty("postbtn")))).isDisplayed();
    }

    //validate alert message if we click on post button without comment text
    public String validatealertmsg() throws InterruptedException {
        props = PropertiesFile.prop;
        driver.findElement(By.xpath(props.getProperty("postbtn"))).click();
        Thread.sleep(1000);
        String alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("alert")))).getText();
        System.out.println(alertMessage);
        return alertMessage;
    }

    //enter any comment in the editable text area
    public boolean entercomment() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(1000);
        WebElement entercomment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("input"))));
        entercomment.click();
        return entercomment.isDisplayed();

    }

    //------------------------------------------- Metrics Tab ------------------------------------


    // verify last pushed comment is same as it was entered last time
    public boolean validate_pushcomment(String comment) throws InterruptedException {
        List<WebElement> listofcomments = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("showcommentsection") + "/div")));
        String temp_path = props.getProperty("showcommentsection") + "/div[" + String.valueOf(listofcomments.size()) + "]/h4";
        String getcomment = driver.findElement(By.xpath(temp_path)).getText();
        System.out.println("current founded comment : " + getcomment);
        return comment.matches(getcomment);

    }

    //check the feature of entering comments
    public boolean entercomment(String tab) throws InterruptedException {
        props = PropertiesFile.prop;
        //super.clickonTab(props.getProperty(tab));
        Thread.sleep(3000);
        WebElement cm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("commenticon"))));
        cm.click();
        Thread.sleep(3000);
        String comment = tab + "-" + props.getProperty("commentText");
        boolean status = false;
        WebElement commentbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("input"))));
        if (commentbox.isDisplayed()) {
            commentbox.click();
            commentbox.clear();
            commentbox.sendKeys(comment);
            enteredcomment.add(comment);
        }

        WebElement pstbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("postbtn"))));
        if (commentbox.isDisplayed() && pstbtn.isDisplayed()) {
            pstbtn.click();
            Thread.sleep(5000);
            if (this.validate_pushcomment(comment)) {
                String tabname = "abc";
                tabname = driver.findElement(By.xpath(props.getProperty("currentselectedtab"))).getText();
                //System.out.println("current tabname : "+tabname);
                if (commentRecord.containsKey(tabname))
                    commentRecord.get(tabname).add(comment);
                else {
                    ArrayList<String> temp_list = new ArrayList<String>();
                    temp_list.add(comment);
                    commentRecord.put(tabname, temp_list);
                }
                status = true;
            }
        }
        WebElement cm1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(props.getProperty("closebtn"))));
        cm1.click();
        return status;
    }

    //verify entered comment is same as it is showing in comment box
    public boolean verify_pushedcomment(String tab) throws InterruptedException {
       // super.clickonTab(props.getProperty(tab));
        Thread.sleep(3000);
        WebElement cm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("commenticon"))));
        cm.click();
        Thread.sleep(3000);
        List<WebElement> listofcomments = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("showcommentsection") + "/div")));
        String temp_path = props.getProperty("showcommentsection") + "/div[" + String.valueOf(listofcomments.size()) + "]/h4";
        String getcomment = driver.findElement(By.xpath(temp_path)).getText();
        System.out.println("current founded comment : " + getcomment);

        String tabname = "abc";
        tabname = driver.findElement(By.xpath(props.getProperty("currentselectedtab"))).getText();

        ArrayList<String> value = commentRecord.get(tabname);
        String comment = value.get(value.size() - 1);
        System.out.println("comment founded comment from list : " + comment);
        WebElement cm1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(props.getProperty("closebtn"))));
        cm1.click();
        return comment.matches(getcomment);

    }



}
    
    