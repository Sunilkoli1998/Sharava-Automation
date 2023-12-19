package com.Shrava;

import com.Shrava_sanity.Comment;
import com.Shrava_sanity.ListReport;
import com.Shrava_sanity.ReportPage;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ReportPageTest extends BrowserSetUp {

    public static Properties props;
    public ReportPage reportpage;
    private final Logger logger = LogManager.getLogger(ReportPageTest.class);
    public BrowserSetUp pageload;
    public ListReport listreport;
    private Comment comment;



    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        reportpage = new ReportPage(driver);
        comment=new Comment(driver);
        props = PropertiesFile.prop;
    }

//--------------------------- List Report Page ----------------------------------


    @Parameters({"product"})
    @Test(priority = 1, enabled = true)
    public void searchsampleID() throws InterruptedException {
        boolean samplestatus = reportpage.searchSampelID();
        test = extent.createTest("Report Page Test");
        Assert.assertTrue(samplestatus);
        logger.info("sample is searched for get the reprot for page");
    }

    @Test(priority = 2)
    public void verifyPresenceOfDisclaimer() throws Exception {
        String disclaimerNote = reportpage.verifyDisclaimerNote();
        Assert.assertEquals(disclaimerNote, "Disclaimer :  This report is meant for review and subsequent approval from a certified pathologist. Under no circumstances shall this report be provided to the patient without the approval from a certified pathologist.");
        logger.info("Sample id is verified successfully " + disclaimerNote);
    }



    @Test(priority = 3,enabled = false)
    public void verifySlideID() throws Exception {
        String slidID = reportpage.verifySlideID();
        Assert.assertEquals(slidID, "Sample ID: " + props.getProperty("searchSampleID"));
        logger.info("Sample id is verified successfully " + slidID);
    }



    @Test(priority = 4, enabled = false)
    public void verifyAuditLog() throws InterruptedException {
        String audiLogText = reportpage.auditLog();
        Assert.assertEquals(audiLogText, "Audit Logs");
        logger.info("audit log is verified on report page");
    }

    @Test(priority = 5, enabled = true)
    public void verifyslideDetailAtReportPage() throws InterruptedException  {
        String slideDetails =reportpage.verifyslidedetails();
        Assert.assertEquals(slideDetails,"Description:Submitted By:Product Version:Chamber No:Submitted At:");

        logger.info("details are verified"+slideDetails);
    }



    @Test(priority = 6, enabled = true)
    public void verifyHeaderOnReportPage() throws Exception {
       // Thread.sleep(2000);
        String actualHeader = reportpage.reportPageHeader();
        Assert.assertEquals(actualHeader, "Summary\n" +
                "Microscopic View\n" +
                "Quality Assessment");
        logger.info(" expected header is present on report page");
    }

    @Test(priority = 7)
    public void verifyurineReportHeader() throws Exception {
        String pbsReportHeadings = reportpage.pbsReportHeader();
        Assert.assertEquals(pbsReportHeadings,
                "Name,Count,Ref Range,Result,,");
        logger.info(" expected urine  header is present on report page");
    }


    @Test(priority = 8)
    public void verifyAllReportsName() throws Exception {
        String actualReportsName = reportpage.getListOfReportName();
        Assert.assertEquals(actualReportsName, "RBC,WBC,Micro Org,Yeast,Cast,Crystal,Calcium oxalate,Calcium oxalate monohydrate,Uric acid,Triple phosphate,Other crystals,Epithelial,Spermatozoa,Unclassified,");
        logger.info("expected reports types are present on report page");
    }





    @Test(priority = 11,enabled = false)
    public void verifyingOriginalReport() {
        String originalText = reportpage.originalReport();
        Assert.assertEquals(originalText, "Original");
        logger.info("original repport is visible on report page");
    }
    @Test(priority = 12)
    public void verifyDownloadReport() throws InterruptedException {
        String actualText = reportpage.downloadReport();
        Assert.assertEquals(actualText, "XLS\n" +
                "CSV");
        logger.info(" xls report format is present on downloads at report page ");
    }



    @Test(priority = 13,enabled = false)
    public void verifyreassingedWithDifferentUsers() throws InterruptedException {
        Assert.assertTrue(reportpage.reassingedWithDifferentUsers());
        logger.info("reassigned user name is displayed on ReassignedBy field ");
    }


    @Test(priority = 15,enabled = false)
    public void verifyAllAccessibilityButtonText() throws InterruptedException {
        String actualText = reportpage.reportAccessibilityButton();
        Assert.assertEquals(actualText, "restore Update\n" +
                "done Approve\n" +
                "close Reject,");
    }



    //verify Shonit report page is successfully open or not
    @Test(priority = 18, enabled = true)
    public void verifyReportPage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Assert.assertTrue(reportpage.verifyReportPage());
        // String actual= reportpage.verifyReportPage();
        // Assert.assertEquals(actual,"");
        logger.info("Report page is opened successfully ");
    }













    @Test(priority = 21,enabled = false)
    public void verifyReassignmentdetail() {
        String reassignedBy = reportpage.reassignmentdetails();
        Assert.assertEquals(reassignedBy, "Reassigned By:\n" + props.getProperty("username3"));
        logger.info("reassiged reviewer detail is verified on summary page" +reassignedBy );
    }

    @Test(priority = 22,enabled = true)
    public void verifyflageWithColourCode() {
        String flage = reportpage.verifyTheNorAbReferenceNote();
        Assert.assertEquals(flage, "x Normalx Abnormalx Reference");
        logger.info("reassiged reviewer detail is verified on summary page" +flage );
    }
    @Test(priority = 23,enabled = true)
    public void verifyPresenceOfApproveButton() {
        String button = reportpage.verifyPresenceOfApproveButton();
        Assert.assertEquals(button, "done Approve");
        logger.info("Approve and reject buttons are present"+button );
    }

    @Test(priority = 24,enabled = true)
    public void verifyPresenceOfRejectButton() {
        String button = reportpage.verifyPresenceOfRejectButton();
        Assert.assertEquals(button, "close Reject");
        logger.info("Approve and reject buttons are present"+button );
    }

    @Test(priority = 25,enabled = true)
    public void verifyPresenceOfDetectedAndNondetectedNote() {
        String button = reportpage.verifyDetectedAndNonDetectedNote();
        Assert.assertEquals(button, "D - DetectedND - Not DetectedNS - Not significant");
        logger.info("Detected and non detected note is present "+button );
    }
    @Test(priority = 26,enabled = false)
    public void verifyPresenceOfEditButton() {
        String button = reportpage.verifyPresenceOfEditButton();
        Assert.assertEquals(button, "edit\n" +
                "Edit");
        logger.info("edit button is present"+button );
    }




    @Test(priority = 29,enabled = true)
    public void verifyExandThepatch() throws InterruptedException {
       String extractedCell =reportpage.Expanding1StPatch();
        Assert.assertEquals(extractedCell,"- Extracted Cell");
        logger.info("Full patch view opened "+extractedCell );
    }

    @Test(priority = 30,enabled = true)
    public void verifyCellNamesInFullpatchView() throws InterruptedException {
        String CellNames =reportpage.verifyListOfcellNmaesInfullpatchView();
        Assert.assertEquals(CellNames,"RBCWBCEpithelialCastMicro OrgCrystalYeastSpermatozoaUnclassified");
        logger.info("Cell names are verified and cell names are "+CellNames );
    }
    @Test(priority = 31,enabled = true)
    public void verifyPresenceAndFunctionalalityOfUpandDownArrow() throws InterruptedException {
        reportpage.verifyPresenceAndFunctionalalityOfUpandDownArrow();
        logger.info("down and up are present and working fine ");
    }

    @Test(priority = 32,enabled = true)
    public void vrifyFuctionalityOfZoomInbutton() throws InterruptedException {
        String zoomLevel=reportpage.vrifyFuctionalityOfZoomInbutton();
        Assert.assertEquals(zoomLevel,"50x");
        logger.info("zoom in button is woking fine");
    }

    @Test(priority = 33,enabled = true)
    public void vrifyFuctionalityOfZoomOutbutton() throws InterruptedException {
        String zoomLevel=reportpage.vrifyFuctionalityOfZoomOutbutton();
        Assert.assertEquals(zoomLevel,"40x");
 logger.info("zoom Out button is woking fine");
    }

    @Test(priority = 34,enabled = true)
    public void verifyResetButtonFuctionality() throws Exception {
        String zoomLevel=reportpage.verifyResetButtonFuctionality();
        Assert.assertEquals(zoomLevel,"40x");

        logger.info("reset button is woking fine");
    }

    @Test(priority = 35,enabled = false)
    public void verifyOptionPresentIntheDropDown() throws Exception {
        String options=reportpage.verifyOptionPresentIntheDropDown();
        Assert.assertEquals(options,"40x100x");
        logger.info("Option are verified and options are "+options);
    }
    @Test(priority = 36,enabled = true)
    public void SelectionOf100xoption() throws Exception {
        String options=reportpage.SelectionOf100xoption();
        Assert.assertEquals(options,"100x");
        logger.info("100x option is selected "+options);
    }




    @Test(priority = 37,enabled = true)
    public void selectionOf40xOption() throws Exception {
        String options=reportpage.selectionOf40xOption();
        Assert.assertEquals(options,"40x");
        logger.info("40x option is selected "+options);
    }
    @Test(priority = 38,enabled = true)
    public void verifyRollOverImageNote()  {
        String note=reportpage.verifyRollOverImageNote();
        Assert.assertEquals(note,"Roll over image to Zoom");
        logger.info("The note is present and note is "+note);
    }
    @Test(priority = 39,enabled = true)
    public void verifyDefaultCheckBoxselection()  {
        String defautCheck=reportpage.verifyDefaultCheckBoxselection();
        Assert.assertEquals(defautCheck,"true");
        logger.info("Bydefault check box is selected "+defautCheck);
    }


    @Test(priority = 40,enabled = true)
        public void verifyCloasingOfFullPatch() throws InterruptedException {
        String extractedCell =reportpage.closingFullpatchview();
        Assert.assertEquals(extractedCell,"Urine Microscopy Report");
        logger.info("Full patch view closed "+extractedCell );
    }



}

