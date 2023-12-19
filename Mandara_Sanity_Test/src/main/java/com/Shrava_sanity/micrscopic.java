package com.Shrava_sanity;

import com.data.ImgDiffPercent;
import com.data.MandaraData.ReadMandaraData;
import com.data.Shrava_data.PropertiesFile;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class micrscopic {

    public Actions actions;
    public static Properties props;
    public WebDriver driver;
    public static WebDriverWait wait;
    static String reviewerValue;
    public Login login;

    public ReadMandaraData readmandaradata;
    public ImgDiffPercent imagecomp;
    private final Logger logger = LogManager.getLogger(micrscopic.class);
    private static DecimalFormat df = new DecimalFormat("0.00");
    public String selectedrowdetails = "abc";
    String curDir = System.getProperty("user.dir");


    public micrscopic(WebDriver driver) throws Exception {
        this.driver = driver;
        Actions actions = new Actions(driver);

        props = PropertiesFile.prop;
        PropertiesFile.readMandaraHomePropertiesFile();
        PropertiesFile.readShonitPropertiesFile();
        PropertiesFile.readShonitListReportFile();
        PropertiesFile.readSearchFilterFile();
        PropertiesFile.readMicroscopicViewFile();

        int time = Integer.parseInt(props.getProperty("timeout"));
        wait = new WebDriverWait(driver, time);
        readmandaradata = new ReadMandaraData();
        imagecomp = new ImgDiffPercent();
    }

  public static String  verifymicroscopctab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("microscopictab")))).click();

      String text=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("microscopictab")))).getText();
      return text;

  }
    public static boolean verifyZoomInicon()throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'add_circle_outline')]"))).isDisplayed();

    }
    // Verify the presence of ZOOMOUT icon in patches.
    public static boolean verifyZoomouticon()throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'remove_circle_outline')]"))).isDisplayed();

    }


    // Verify the presence of RESET icon in patches.
    public boolean verifyRESETicon()throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'replay')]"))).isDisplayed();

    }
    //verify the functionality of zoomin button
    public boolean verifyImageZoomIn() throws InterruptedException, IOException {
        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String curr = curDir + "/src/test/java/com/temp/zoomin-current.png";
        FileUtils.copyFile(scrFile, new File(curr));
        Thread.sleep(2000);

        Actions action = new Actions(driver);
        for (int i = 0; i < 4; i++)
            action.moveToElement(driver.findElement(By.cssSelector(props.getProperty("zoomin")))).click().build().perform();
        Thread.sleep(2000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/zoomin-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));
        double differ = imagecomp.getDifferencePercent(curr, difference);
        return differ > 0.0;

    }

    //verify the functionality of reset button
    public boolean verifyImageSizeReset() throws InterruptedException, IOException {
        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/reset-current.png";
        FileUtils.copyFile(scrFile, new File(current));
        Thread.sleep(2000);

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.cssSelector(props.getProperty("resize")))).click().build().perform();
        Thread.sleep(4000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        String difference = curDir + "/src/test/java/com/temp/reset-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));
        double differ = imagecomp.getDifferencePercent(current, difference);
        return differ > 0.0;
    }


    public boolean verifyImageZoomOut() throws InterruptedException, IOException {
        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/zoomout-current.png";
        FileUtils.copyFile(scrFile, new File(current));
        Thread.sleep(2000);

        Actions action = new Actions(driver);
        for (int i = 0; i < 4; i++)
            action.moveToElement(driver.findElement(By.cssSelector(props.getProperty("zoomout")))).click().build().perform();
        Thread.sleep(2000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/zoomout-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));

        double differ = imagecomp.getDifferencePercent(current, difference);
        logger.info(current);
        logger.info(differ);
        return differ > 0.0;

    }

    // Verify the presence of navigation icons in patches.
    public boolean verifynexticon() throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@id='right']"))).isDisplayed();

    }

    // Verify the presence of navigation Goto down icon in patches.
    public boolean verifygotobottomicon() throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@id='bottom']"))).isDisplayed();

    }
    public boolean verifyGoToNextImage() throws InterruptedException, IOException {

        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/nextimg-current.png";
        FileUtils.copyFile(scrFile, new File(current));
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(props.getProperty("nextimage")))).click().build().perform();
        Thread.sleep(3000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/nextimg-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));

        double differ = imagecomp.getDifferencePercent(current, difference);
        logger.info(differ);
        return differ > 0.0;

    }
    // Verify the presence of navigation previous icon in patches.
    public boolean verifypreviousicon() throws InterruptedException{
        // driver.findElement(By.xpath("")).click();
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@id='left']"))).isDisplayed();

    }

    //find the prev image after click on prev button for the opened preview window
    public boolean verifyGoToPrevImage() throws InterruptedException, IOException {

        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/previmg-current.png";
        FileUtils.copyFile(scrFile, new File(current));

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(props.getProperty("previmage")))).click().build().perform();
        Thread.sleep(3000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/previmg-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));

        double differ = imagecomp.getDifferencePercent(current, difference);
        return differ > 0.0;

    }
    public boolean verifyGoToTopImage() throws InterruptedException, IOException {

        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/previmg-current.png";
        FileUtils.copyFile(scrFile, new File(current));

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(props.getProperty("topimage")))).click().build().perform();
        Thread.sleep(3000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/previmg-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));

        double differ = imagecomp.getDifferencePercent(current, difference);
        return differ > 0.0;

    }
    public boolean verifyGoToBottomImage() throws InterruptedException, IOException {

        props = PropertiesFile.prop;
        File scrFile = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String current = curDir + "/src/test/java/com/temp/previmg-current.png";
        FileUtils.copyFile(scrFile, new File(current));

        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath(props.getProperty("bottomimage")))).click().build().perform();
        Thread.sleep(3000);

        File scrFile1 = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);

        String difference = curDir + "/src/test/java/com/temp/previmg-difference.png";
        FileUtils.copyFile(scrFile1, new File(difference));

        double differ = imagecomp.getDifferencePercent(current, difference);
        return differ > 0.0;

    }
    // Verify the presence of navigation Goto top icon in patches.
    public boolean verifygotoupicon() throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@id='top']"))).isDisplayed();

    }

    public boolean verifypaginationtextbox() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pagination-input']"))).isDisplayed();
    }

    public String enterNumberInTextbox() {
        props = PropertiesFile.prop;
        WebElement inputField = driver.findElement(By.xpath(props.getProperty("inputBox")));
        inputField.clear();
        inputField.sendKeys(props.getProperty("FOVNumber"));
        inputField.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String text = driver.findElement(By.xpath(props.getProperty("selectedNumber"))).getText();
        System.out.println(text);
        return text;
    }

    public String verifyMArkAll() {
        props = PropertiesFile.prop;
        String text = driver.findElement(By.xpath(props.getProperty("markAll"))).getText();
        System.out.println(text);
        //driver.findElement(By.xpath(props.getProperty("checkkbox"))).click();

        return text;
    }

    public boolean minimisingOfFullFov() {
        props = PropertiesFile.prop;
        WebElement zoomIn = driver.findElement(By.tagName("html"));
        // zoomIn.sendKeys(Keys.chord(Keys.COMMAND,Keys.ADD));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom='100%'");
        return true;
    }

    public boolean maximisingOfFullFov() {
        props = PropertiesFile.prop;
        WebElement zoomOut = driver.findElement(By.tagName("html"));
        zoomOut.sendKeys(Keys.chord(Keys.COMMAND, Keys.SUBTRACT));
        //JavascriptExecutor executor=(JavascriptExecutor)driver;
        // executor.executeScript("document.body.style.zoom='100%'");
        return true;
    }
    //verifying the presence of pagination feild
    public boolean verifypresenceofpaginationfeild() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pagination-input']"))).isDisplayed();
    }

    public boolean verifyslideoverviewImageLoaded() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-tab-body/div[1]/div[1]/app-micro-scopic-view[1]/section[1]/div[1]/div[2]/div[2]/app-slide-fov-view[1]/div[1]/div[1]/div[3]"))).isEnabled();
    }

    //verifying the presence of rolloverimagetozoombutton
    public boolean verifypresenceofRolloverimagetozoombutton() {
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-tab-body/div[1]/div[1]/app-micro-scopic-view[1]/section[1]/div[1]/div[1]/app-micro-scopic-dialog[1]/mat-dialog-content[1]/app-fov-canvas-view[1]/div[1]/div[2]/div[1]/div[1]/span[2]/mat-checkbox[1]/label[1]/div[1]"))).isEnabled();
    }


    //method to verify the default status of the mark all check box
    public boolean defaultstatusofmarkallcheckbox() throws InterruptedException {
        props = PropertiesFile.prop;
        //driver.findElement(By.xpath())
        Thread.sleep(2000);
        boolean check = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/mat-list[1]/mat-list-item[10]/div[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();
        System.out.println(check);
        return check;

    }

    //method to verify the status of selected checkbox
    public boolean selectedstatusofmarkallcheckbox() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement checkbox = driver.findElement(By.xpath("//section[1]/mat-list[1]/mat-list-item[9]/div[1]/mat-checkbox[1]/label[1]/div[1]"));
        Thread.sleep(2000);
        checkbox.click();
        Thread.sleep(10000);
        boolean ccc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/mat-list[1]/mat-list-item[9]/div[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();
        System.out.println(ccc);
        return ccc;
    }

    public String SlideoverviewNOTE() throws InterruptedException {
        props = PropertiesFile.prop;
        WebElement note = driver.findElement(By.xpath("//span[contains(text(),'Use Up/Down arrow to ensure visiting all the FOVs')]"));
        String Note = note.getText();
        return Note;

    }
    //Verify the patch number appearing at the top left corner when the user clicks on any cell patch.
    public String verifyPATCHnumberinexpandedview() throws InterruptedException{
        props = PropertiesFile.prop;
        driver.findElement(By.xpath("//div[@class='fov-dot'][normalize-space()='0']")).click();
        Thread.sleep(1000);WebElement FOVNO=  driver.findElement(By.xpath("//div[contains(text(),'FOV ')]"));
        String  fovno =FOVNO.getText();
        System.out.println(fovno);
        return fovno;
    }

    public String patchesgettingchange() throws InterruptedException {
        props = PropertiesFile.prop;
        String fov = "";
        WebElement pagination= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pagination-input']")));
        pagination.clear();
        pagination.sendKeys("1");
        pagination.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        List<WebElement> fovdots = driver.findElements(By.xpath("//mat-tab-body/div[1]/div[1]/app-micro-scopic-view[1]/section/div[1]/div[2]/div/app-slide-fov-view/div[1]/div[1]/div[2]/div"));
        for (int i =0; i <= 5; i++) {
            fovdots.get(i).click();
            Thread.sleep(5000);
            WebElement fovname = driver.findElement(By.xpath("//span[contains(text(),'FOV ')]"));
            String fovnames = fovname.getText();
            fov = fov + " " + fovnames;
        }
        System.out.println(fov);
        return fov;
    }


    public boolean verifyFOVButton() throws InterruptedException{
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'open_with')]"))).isDisplayed();
    }
    //Verify the availability of the Mark All checkbox only for WBC cells
    public boolean  verifymarkallcheckboxinexpandedfov() throws InterruptedException{
        props = PropertiesFile.prop;
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/mat-list[1]/mat-list-item[9]/div[1]/mat-checkbox[1]/label[1]/div[1]"))).isDisplayed();
    }

    //Verify the deselection of Mark all checkbox.
    public boolean disselectionofcbeckbox() throws InterruptedException{
        props = PropertiesFile.prop;
        for(int i=1;i<=3;i++){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/mat-list[1]/mat-list-item[9]/div[1]/mat-checkbox[1]/label[1]/div[1]"))).click();
            Thread.sleep(3000);
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[1]/mat-list[1]/mat-list-item[9]/div[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();
    }


    //Verify the patches getting changed when clicked on the dots present in the slide overview when fov is expanded
    public String patchesgettingchangeonexpandedfovview() throws InterruptedException {
        props = PropertiesFile.prop;
        props = PropertiesFile.prop;
        String fov = "";
        WebElement pagination= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='pagination-input']")));
        pagination.clear();
        pagination.sendKeys("1");
        pagination.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        List<WebElement> fovdots = driver.findElements(By.xpath("//div[@class='fov-dot']"));
        for (int i =1; i <= 4; i++) {
            fovdots.get(i).click();
            Thread.sleep(5000);
            WebElement fovname = driver.findElement(By.xpath("//div[contains(text(),'FOV ')]"));
            String fovnames = fovname.getText();
            fov = fov + " " + fovnames;
        }
        System.out.println(fov);
        return fov;
    }


    //Verify the availability  of roll over zoom on expanded FOV view
    public String  VerifytheROLLOVERZOOMcheckboxinexpandedfovview(){
        props = PropertiesFile.prop;
        WebElement rollover= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Roll over image to Zoom')]")));
        String rolloverzoom=rollover.getText();
        return rolloverzoom;
    }

    // Verify the functionality of roll over zoom when the image is more than 100% zoom
    public String  verifyfunctionalityofROLLOVERZOOMCHECKBOXmorethanhundredpercent() throws InterruptedException {
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        for (int i = 0; i <=6; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'add_circle_outline')]"))).click();
            Thread.sleep(1000);
        }

        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Roll over zoom is disabled, FOV is zoomed more tha')]")));
        String popups = popup.getText();
        return popups;
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'replay')]"))).click();

        //return  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/mat-dialog-container[1]/app-wrapper-dialog-microscopic-view[1]/section[1]/app-expanded-microscopic-view[1]/section[1]/app-micro-scopic-view[1]/section[1]/div[2]/div[1]/app-micro-scopic-dialog[1]/mat-dialog-content[1]/app-fov-canvas-view[1]/div[1]/div[2]/div[1]/div[1]/span[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();
    }

    public String ROLLOVERZOOMCHECKBOXmorethanhundredpercent() throws InterruptedException {
        props = PropertiesFile.prop;
        //driver.findElement(By.xpath("//i[contains(text(),'replay')]")).click();
        for (int i = 1; i <=9; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'add_circle_outline')]"))).click();
            Thread.sleep(1000);
        }
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Roll over zoom is disabled, FOV is zoomed more tha')]")));
        String popups = popup.getText();
        return popups;
    }

    // Verify the presence of Roll over zoom checkbox.
    public boolean VerifytheROLLOVERZOOMcheckbox(){
        props = PropertiesFile.prop;
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Roll over image to Zoom')]"))).isDisplayed();
    }

    //Verify the closing of expanded view.
    public boolean verifyclosingEXPANDEDFOV() throws InterruptedException{
        props = PropertiesFile.prop;
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'close')]"))).click();
        //driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        return true;
    }

    // Verify the functionality of expand button on the FOV.
    public boolean viewexpandedfov(){
        props = PropertiesFile.prop;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("expandImage")))).click();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        return true;
    }

    // Verify the functionality of roll over zoom when the image is less than 100% zoom
    public boolean functionalityofROLLOVERZOOMCHECKBOXlessthannhundredpercent() throws InterruptedException
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/mat-dialog-container[1]/app-wrapper-dialog-microscopic-view[1]/section[1]/app-expanded-microscopic-view[1]/section[1]/app-micro-scopic-view[1]/section[1]/div[2]/div[1]/app-micro-scopic-dialog[1]/mat-dialog-content[1]/app-fov-canvas-view[1]/div[1]/div[2]/div[1]/div[1]/span[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();
    }



    //Verify the functionality of roll over zoom when the image is 100% zoom in expanded fov view
    public boolean fucntionalityofrolloverzoomcheckboxbyhundredpercent() throws InterruptedException{
        props = PropertiesFile.prop;
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'replay')]"))).click();
        for(int i =1;i<=9;i++){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(text(),'add_circle_outline')]"))).click();
            Thread.sleep(2000);
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-fov-canvas-view[1]/div[1]/div[2]/div[1]/div[1]/span[1]/mat-checkbox[1]/label[1]/div[1]"))).isSelected();

    }


    public boolean verifytheecellsofexpandedfovinmicroscopicview(){
        boolean flag=false;
        String cellsname="";
       List<WebElement> cells= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//section[1]/mat-list[1]/mat-list-item/div[1]/div[3]/span[1]/span[1]")));
       for(int i=0;i<=cells.size()-1;i++){
          cellsname=cellsname+cells.get(i).getText()+",";
          logger.info(cellsname);
       }
       if(cellsname.equals("RBC,Pus Cell,Epithelial,Cast,Microscopic Organism,Crystal,Yeast,Rejected"));{
           flag=true;
        }
        return flag;
    }


}