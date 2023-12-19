package com.Shrava;

import com.Shrava_sanity.Comment;
import com.Shrava_sanity.ListReport;
import com.Shrava_sanity.ReportPage;
import com.Shrava_sanity.micrscopic;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class micrscopiocTest extends BrowserSetUp {

    public static Properties props;
    public micrscopic microscopic;
    public ReportPage reportpage;
    private final Logger logger = LogManager.getLogger(micrscopiocTest.class);
    public BrowserSetUp pageload;
    public ListReport listreport;
    private Comment comment;


    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        reportpage = new ReportPage(driver);
        comment = new Comment(driver);
        microscopic = new micrscopic(driver);
        props = PropertiesFile.prop;
    }

//--------------------------- List Report Page ----------------------------------


    @Test(priority = 1, enabled = true)
    public void Verifymicroscopictab() {
        test = extent.createTest("Microscopic view");
        String samplestatus = microscopic.verifymicroscopctab();

        Assert.assertEquals(samplestatus, "Microscopic View");
        logger.info("sample is searched for get the reprot for page");
    }

    //Verify the presence of zoom in icon.
    @Test(priority = 2, enabled = true)
    public void verifyZoomInIcon() throws InterruptedException, IOException {

        if (microscopic.verifyZoomInicon()) {
            Assert.assertTrue(true);
            logger.info("Zoom in icon is verified successfully");
        } else {
            Assert.assertFalse(false);
            logger.info("Zoom in icon is not verified successfully");
        }
    }

    //Verify the presence of zoom out icon.
    @Test(priority = 3, enabled = true)
    public void verifyZoomoutIcon() throws InterruptedException, IOException {

        if (microscopic.verifyZoomouticon()) {
            Assert.assertTrue(true);
            logger.info("Zoom out icon is verified successfully");
        } else {
            Assert.assertFalse(false);
            logger.info("Zoomout icon is not verified successfully");
        }
    }


    //Verify the presence of reset icon.
    @Test(priority = 4, enabled = true)
    public void verifyRESETIcon() throws InterruptedException, IOException {
        if (microscopic.verifyRESETicon()) {
            Assert.assertTrue(true);
            logger.info("RESET icon is verified successfully");
        } else {
            Assert.assertFalse(false);
            logger.info("RESET icon is not verified successfully");
        }
    }

    //Verify the functionality of zoom in,
    @Test(priority = 5, enabled = true)
    public void verifyImageZoomIn() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomIn()) {
            logger.info("The images were zoomed in successfully");
            status = true;
        } else {

            logger.info("The images were not zoomed in successfully");
            Assert.assertTrue(status);
        }
    }


    @Test(priority = 6, enabled = true)
    public void verifyImageSizeReset() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageSizeReset()) {
            logger.info("The images were zoomed in successfully");
            status = true;
        } else {
            logger.info("The images were not zoomed in successfully");
            Assert.assertTrue(status);
        }
    }

    @Test(priority = 7, enabled = true)
    public void verifyImageZoomOut() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomOut()) {
            logger.info("The images were zoomed out successfully");
            status = true;
        } else {
            logger.info("The images were not zoomed out successfully");
            Assert.assertTrue(status);
        }
    }

    // Verify the presence of navigation goto next icons in patch.
    @Test(priority = 8, enabled = true)
    public void verifyGoToNexticon() throws InterruptedException, IOException {
        if (microscopic.verifynexticon()) {
            Assert.assertTrue(true);
            logger.info("Next icon is present");
        } else {
            Assert.assertFalse(false);
            logger.info("Next icon is not present");
        }
    }


    // Verify the presence of navigation goto down icon in patch.
    @Test(priority = 9, enabled = true)
    public void verifyGoTobottomicon() throws InterruptedException, IOException {
        if (microscopic.verifygotobottomicon()) {
            Assert.assertTrue(true);
            logger.info("goto bottom icon is present");
        } else {
            Assert.assertFalse(false);
            logger.info("goto bottom icon is not present");
        }
    }


    //Verify the next and previous buttons to load images
    @Test(priority = 10, enabled = true)
    public void verifyGoToNextImage() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToNextImage()) {
            logger.info("next image is loaded successfully ");
            status = true;
        } else {

            logger.info("Failed to load the next image");
            Assert.assertTrue(status);
        }
    }


    // Verify the presence of navigation goto previous icons in patch.
    @Test(priority = 11, enabled = true)
    public void verifyGoTopreviousicon() throws InterruptedException, IOException {
        if (microscopic.verifypreviousicon()) {
            Assert.assertTrue(true);
            logger.info("previous icon is present");
        } else {
            Assert.assertFalse(false);
            logger.info("previous icon is not present");
        }
    }

    @Test(priority = 12, enabled = true)
    public void verifyGoToPrevImage() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToPrevImage()) {
            logger.info("previous image is loaded successfully ");
            status = true;
        } else {
            logger.info("Failed to load the prev image");
            Assert.assertTrue(status);
        }
    }

    @Test(priority = 13, enabled = true)
    public void verifyGoToDownImage() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToBottomImage()) {
            logger.info("Bottom image is loaded successfully ");
            status = true;
        } else {
            logger.info("Failed to load the bottom image");
            Assert.assertTrue(status);
        }
    }

    // Verify the presence of navigation goto up icon in patch.
    @Test(priority = 14, enabled = true)
    public void verifyGoToupicon() throws InterruptedException, IOException {
        if (microscopic.verifygotoupicon()) {
            Assert.assertTrue(true);
            logger.info("goto up icon is present");
        } else {
            Assert.assertFalse(false);
            logger.info("goto up icon is not present");
        }
    }


    @Test(priority = 15, enabled = true)
    public void verifyGoToUpImage() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToTopImage()) {
            logger.info("Top image is loaded successfully ");
            status = true;
        } else
            logger.info("Failed to load the top image");
        Assert.assertTrue(status);
    }


    //
    //Verify the presence of pagination text box.
    @Test(priority = 16, enabled = true)
    public void verifypaginationtextbox() throws InterruptedException, IOException {
        if (microscopic.verifypaginationtextbox()) {
            Assert.assertTrue(true);
            logger.info("paginationtextbox is present");
        } else {
            Assert.assertFalse(false);
            logger.info("paginationtextbox not present");
        }
    }


    //Verify the navigation using pagination text box.
    @Test(priority = 17, enabled = true)
    public void verifyenterNumberInTextbox() {
        String actualFOVnumber = microscopic.enterNumberInTextbox();
        Assert.assertEquals(actualFOVnumber, props.getProperty("FovNumberatCanvas"));
        logger.info(" same FOV Number displayed on  microscopic view cell ");
    }

    //Verify the availability of the Mark All checkbox only for WBC cells.
    @Test(priority = 18, enabled = true)
    public void verifyMarkAll() {
        String actualtext = microscopic.verifyMArkAll();
        Assert.assertEquals(actualtext, "Mark All");
        logger.info(" Mark All text is present on WBC tab ");
    }

    @Test(priority = 19, enabled = true)
    public void verifyingMinimisingOfFullFov() {
        Assert.assertTrue(microscopic.minimisingOfFullFov());
        logger.info("The images were zoomed in successfully");
    }

    @Test(priority = 20, enabled = true)
    public void verifyingMaximisingOfFullFov() {
        Assert.assertTrue(microscopic.maximisingOfFullFov());
        logger.info("The images were zoomed out successfully");
    }


    @Test(priority = 21, enabled = true)
    public void clickoncommenticon() throws InterruptedException {
        Assert.assertTrue(comment.clickoncommentIcon());
        logger.info("Clicked on Comment Icon Successfully");
    }

    @Test(priority = 22, enabled = true)
    public void loadcommentbox() throws InterruptedException {
        Assert.assertTrue(comment.commentboxlaod());
        logger.info("Comment Box laoded succssfully");
    }

    @Test(priority = 23, enabled = true)
    public void validatebox() throws InterruptedException {
        Assert.assertEquals(comment.validatecommentbox(), "Comments");
        logger.info("Comment Box is verified successfully");
    }

    @Test(priority = 24, enabled = true)
    public void closecommentbox() throws InterruptedException {
        Assert.assertTrue(comment.closecommentbox());
        logger.info("Clicked on Comment close Icon Successfully");
    }

    @Test(priority = 25, enabled = true)
    public void clickoncommenticon1() throws InterruptedException {
        comment.clickonCommentIcon();
        logger.info("Clicked on Comment Icon Successfully");
    }

    @Test(priority = 26, enabled = true)
    public void checkuser() throws InterruptedException {
        Assert.assertTrue(comment.checkuser());
        logger.info("user can update the comment");
    }


    @Test(priority = 27, enabled = true)
    public void checkpostbtnclickable() throws InterruptedException {
        Assert.assertTrue(comment.checkpostbtnclickable());
        logger.info("Post button is clickable ");
    }

    @Test(priority = 28, enabled = true)
    public void checkaleartmsg() throws InterruptedException {
        String actualAlerMessage = comment.validatealertmsg();
        Assert.assertEquals(actualAlerMessage, "×\n" +
                "Oops!\n" +
                "Please enter your comment");
        logger.info("Alert message is verified");
    }

    @Test(priority = 29, enabled = true)
    public void checkcommentbox() throws InterruptedException {
        Assert.assertTrue(comment.entercomment());
        logger.info("comment box is editable for entring the comments ");
    }

    //----------------------- Validation of comments pushing in all tab  ------------------

    @Test(priority = 30, enabled = true)
    public void MICROSCOPY_entercomment() throws InterruptedException {
        String tab = "microscopytab";
        Assert.assertTrue(comment.entercomment(tab));
        logger.info("comment pushed successfully for wbc tab");
    }

    @Test(priority = 31, enabled = true)
    public void MICROSCOPY_checkcomment() throws InterruptedException {
        String tab = "microscopytab";
        Assert.assertTrue(comment.verify_pushedcomment(tab));
        logger.info("comment verificaiton Done successfully for wbc tab");
    }



    //Verify the presence of slide overview in Microscopic View tab.
    @Test(priority = 32, enabled = true)
    public void verifyslideoverImageLoaded() throws InterruptedException {
        if (microscopic.verifyslideoverviewImageLoaded()) {
            Assert.assertTrue(true);
            logger.info("Images is loaded in the canvas ");
        } else {
            logger.info("failed to load images in the canvas ");
            Assert.assertTrue(false);
        }
    }

    @Test(priority = 33, enabled = true)
    public void verifytheRolloverimagetozoombutton() throws InterruptedException {
        if (microscopic.verifypresenceofRolloverimagetozoombutton()) {
            Assert.assertTrue(true);
            logger.info("Rolloverimagetozoom is verified");
        } else {
            logger.info("Rolloverimagetozoom is not presented ");
            Assert.assertTrue(false);
        }

    }


    @Test(priority = 34, enabled = true)
    public void verifythepaginationfeild() throws InterruptedException {
        if (microscopic.verifypresenceofpaginationfeild()) {
            Assert.assertTrue(true);
            logger.info("pagination feild is displayed");
        } else {
            logger.info("pagination feild is not presented ");
            Assert.assertTrue(false);
        }

    }

    //verifying the default status of the markall check box
    @Test(priority = 35, enabled = true)
    public void verifythedefaultstatusofmarkallcheckbox() throws InterruptedException {
        if (microscopic.defaultstatusofmarkallcheckbox()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("defaultstatusofmarkallcheckbox is enabled");
        } else {
            Assert.assertFalse(false);
            logger.info("defaultstatusofmarkallcheckbox is disabled ");
        }
    }


    //verifying the status of the marl all check box after selecting the checkbox
    @Test(priority = 36, enabled = false)
    public void verifythestatusofselectedcheckbox() throws InterruptedException {
        if (microscopic.selectedstatusofmarkallcheckbox()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("selectedstatusofmarkallcheckbox is enabled");
        } else {
            Assert.assertFalse(false);
            logger.info("selectedstatusofmarkallcheckbox is disabled ");
        }
    }


    //Verify the notes present in the Slide overview.
    @Test(priority = 38, enabled = true)
    public void verifytheNOTEonslideoverview() throws InterruptedException {
        String NOTE = microscopic.SlideoverviewNOTE();
        Assert.assertEquals(NOTE, "Use Up/Down arrow to ensure visiting all the FOVs");
        logger.info("Verified the NOTE on Slideoverview page");
    }


    //Verify the patches getting changed when clicked on the dots present in the slide overview.

    @Test(priority =39, enabled = true)
    public void verifythepatchesgettingchange() throws InterruptedException {
        String fovnames = microscopic.patchesgettingchange();
        Assert.assertEquals(fovnames, " FOV 1 FOV 2 FOV 3 FOV 4 FOV 5 FOV 6");
    }


    @Test(priority = 40, enabled = true)
    public void verifytheecellsofmicroscopicview(){
        Assert.assertTrue(  microscopic.verifytheecellsofexpandedfovinmicroscopicview());
        logger.info("Succesfully verified the cells of the microscopic view");
    }


    //Verify the availability of expand button on FOV.
    @Test(priority = 41, enabled = true)
    public void verifyavailabilityofExpandFOVButton() throws InterruptedException {
        if (microscopic.verifyFOVButton()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("FOV  BUTTON is present");
        } else {
            Assert.assertFalse(false);
            logger.info("FOV  BUTTON is not present ");
        }
    }



    //// Verify the functionality of expand button on the FOV.
    @Test(priority = 42, enabled = true)
    public void verifyEXPANDEDFOV() {
        boolean status = microscopic.viewexpandedfov();
        Assert.assertTrue(status);
        logger.info(" Microscopic Image is expanded successfully to view the full Image  ");
    }


    //Verify the functionality of roll over zoom when the image is 100% zoom in expanded fov view
    @Test(priority = 43, enabled = true)
    public void verifyfucntionalityofrolloverzoomcheckboxbyhundredpercent() throws InterruptedException {
         if (microscopic.fucntionalityofrolloverzoomcheckboxbyhundredpercent()) {
            Assert.assertTrue(true);
            logger.info("ROLl oVER ZOOM checkbox is defaultly checked ");
            Assert.assertFalse(false);
            logger.info("ROLl oVER ZOOM checkbox is not checked ");
        }
    }


    //Verify the functionality of roll over zoom when the image is more than 110% zoom in expanded fov
    @Test(priority = 43, enabled = true)
    public void verifyfunctionalityofROLLOVERZOOMCHECKBOXlmorethanhundredprcent() throws InterruptedException {
        String popup = microscopic.verifyfunctionalityofROLLOVERZOOMCHECKBOXmorethanhundredpercent();
        System.out.println(popup);
        Assert.assertEquals(popup, "Roll over zoom is disabled, FOV is zoomed more than 100x");
    }

    @Test(priority = 44, enabled = false)
    //Verify the functionality of roll over zoom when the image is less than 110% zoom in expanded view
    public void verifyfunctionalityofROLLOVERZOOMCHECKBOXlessthanhundredprcent() throws InterruptedException {
        if (microscopic.functionalityofROLLOVERZOOMCHECKBOXlessthannhundredpercent()) {
            Assert.assertTrue(true);
            logger.info("ROLl oVER ZOOM checkbox is checked after zooming less than 100%  ");
        } else {
            Assert.assertFalse(false);
            logger.info("ROLl oVER ZOOM checkbox is unchecked after zooming less than 100%");
        }
    }


    //Verify the zoom in functionality in expandedfov,
    @Test(priority = 45, enabled = true)
    public void verifyImageZoomInExpandedfov() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomIn()) {
            logger.info("The images were zoomed in successfully");
            status = true;
        } else
            logger.info("The images were not zoomed in successfully");
        Assert.assertTrue(status);
    }


    //Verify the zoom resetfunctionality in expandedfov,
    @Test(priority = 46, enabled = true)
    public void verifyImageSizeresetExpandedfov() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageSizeReset()) {
            logger.info("The images were SizeReset successfully");
            status = true;
        } else
            logger.info("The images were not SizeReset successfully");
        Assert.assertTrue(status);
    }

    //Verify the zoom out functionality in expandedfov,
    @Test(priority = 47, enabled = true)
    public void verifyImageZoomoutExpandedfov() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomOut()) {
            logger.info("The images were zoomed out successfully");
            status = true;
        } else
            logger.info("The images were not zoomed out successfully");
        Assert.assertTrue(status);
    }


    // Verify the presence of navigation goto next icons in Expanded patch view.
    @Test(priority = 48, enabled = true)
    public void verifyGoToNexticonINexpandedpatchview() throws InterruptedException, IOException {
        if (microscopic.verifynexticon()) {
            Assert.assertTrue(true);
            logger.info("Next icon is present in Expanded patch view ");
        } else {
            Assert.assertFalse(false);
            logger.info("Next icon is not present in Expanded patch view");
        }
    }


    // Verify the presence of navigation goto up icon in in Expanded patch view.
    @Test(priority = 49, enabled = true)
    public void verifyGoToupiconINexpandedpatchview() throws InterruptedException, IOException {
        if (microscopic.verifygotoupicon()) {
            Assert.assertTrue(true);
            logger.info("goto up icon is present in Expanded patch view");
        } else {
            Assert.assertFalse(false);
            logger.info("goto up icon is not present in Expanded patch view");
        }
    }


    // Verify the presence of navigation goto down icon  in inExpanded patch view.
    @Test(priority = 50, enabled = true)
    public void verifyGoTobottomiconINexpandedpatchview() throws InterruptedException, IOException {
        if (microscopic.verifygotobottomicon()) {
            Assert.assertTrue(true);
            logger.info("goto bottom icon is present in Expanded patch view");
        } else {
            Assert.assertFalse(false);
            logger.info("goto bottom icon is not present in Expanded patch view");
        }
    }


    //Verify the next and previous buttons to load images in Expanded patch view
    @Test(priority = 51, enabled = true)
    public void verifyGoToNextImageINexpandedpatchview() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToNextImage()) {
            logger.info("next image is loaded successfully in Expanded patch view ");
            status = true;
        } else
            logger.info("Failed to load the next image in Expanded patch view");
        Assert.assertTrue(status);
    }


    // Verify the presence of navigation goto previous icons  in Expanded patch view.
    @Test(priority = 52, enabled = true)
    public void verifyGoTopreviousiconINexpandedpatchview() throws InterruptedException, IOException {
        if (microscopic.verifypreviousicon()) {
            Assert.assertTrue(true);
            logger.info("previous icon is present in Expanded patch view");
        } else {
            Assert.assertFalse(false);
            logger.info("previous icon is not present in Expanded patch view");
        }
    }

    @Test(priority = 53, enabled = true)
    public void verifyGoToPrevImageINexpandedpatchview() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToPrevImage()) {
            logger.info("previous image is loaded successfully in Expanded patch view");
            status = true;
        } else
            logger.info("Failed to load the prev image in Expanded patch view");
        Assert.assertTrue(status);
    }

    @Test(priority = 54, enabled = true)
    public void verifyGoToDownImageINexpandedpatchview() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToBottomImage()) {
            logger.info("Bottom image is loaded successfully in Expanded patch view ");
            status = true;
        } else
            logger.info("Failed to load the bottom image in Expanded patch view");
        Assert.assertTrue(status);
    }

    @Test(priority = 55, enabled = true)
    public void verifyGoToUpImageINexpandedpatchview() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyGoToTopImage()) {
            logger.info("Top image is loaded successfully in Expanded patch view ");
            status = true;
        } else
            logger.info("Failed to load the top image in Expanded patch view");
        Assert.assertTrue(status);
    }


    //
    //Verify the presence of pagination text box in Expanded patch view.
    @Test(priority = 56, enabled = true)
    public void verifypaginationtextboxINexpandedpatchview() throws InterruptedException, IOException {
        if (microscopic.verifypaginationtextbox()) {
            Assert.assertTrue(true);
            logger.info("paginationtextbox is present in Expanded patch view");
        } else {
            Assert.assertFalse(false);
            logger.info("paginationtextbox not present in Expanded patch view");
        }
    }

    //Verify the patch number appearing at the top left corner when the user clicks on any cell patch.
    @Test(priority = 57, enabled = true)
    public void verifypatchnumberINexpandedpatchview() throws InterruptedException, IOException {
        String fovno = microscopic.verifyPATCHnumberinexpandedview();
        Assert.assertEquals(fovno, "FOV 1");
        logger.info("FOV no is matching when we click on the patch");
    }


    //verifying the default status of the markall check box when FOV is expanded.
    @Test(priority = 58, enabled = true)
    public void verifythedefaultstatusofmarkallcheckboxinexpandedfov() throws InterruptedException {
        if (microscopic.defaultstatusofmarkallcheckbox()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("defaultstatusofmarkallcheckboxinexpandedfov is enabled");
        } else {
            Assert.assertFalse(false);
            logger.info("defaultstatusofmarkallcheckboxinexpandedfov is disabled ");
        }
    }

    //verifying the status of the marl all check box after selecting the checkbox in expanded fov.
    @Test(priority = 59, enabled = false)
    public void verifythestatusofselectedcheckboxinexpandedfov() throws InterruptedException {
        if (microscopic.selectedstatusofmarkallcheckbox()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("statusofselectedcheckboxinexpandedfov is enabled");
        } else {
            Assert.assertFalse(false);
            logger.info("statusofselectedcheckboxinexpandedfov is disabled ");
        }
    }


    //Verify the availability of the Mark All checkbox only for WBC cells
    @Test(priority = 60, enabled = false)
    public void verifymarkallcheckboxinexpandedfov() throws InterruptedException {
        if (microscopic.verifymarkallcheckboxinexpandedfov()) {
            Assert.assertTrue(true);
            //  Assert.assertFalse(false);
            logger.info("markall checkbox in expandedfov is present");
        }
    }

    //Verify the deselection of Mark all checkbox.
    @Test(priority = 61, enabled = false)
    public void verifydisselectionofcbeckbox() throws InterruptedException {
        if (microscopic.disselectionofcbeckbox()) {
            Assert.assertTrue(true);
            logger.info(" verification of the disselection checkbox in expandedfov is failed  ");
        } else {
            Assert.assertFalse(false);
            logger.info("successfully disselected the markall checkbox");
        }
    }


    //Verify the patches getting changed when clicked on the dots present in the slide overview.
    @Test(priority = 63, enabled = true)
    public void verifythepatchesgettingchangeinexpandedfovview() throws InterruptedException {
        String fovnames = microscopic.patchesgettingchangeonexpandedfovview();
        Assert.assertEquals(fovnames, " FOV 2 FOV 3 FOV 4 FOV 5");
    }

    //Verify the notes present in the Slide overview when the fov view is expanded.
    @Test(priority = 64, enabled = true)
    public void verifytheNOTEonExpandedfovview() throws InterruptedException {
        String NOTE = microscopic.SlideoverviewNOTE();
        Assert.assertEquals(NOTE, "Use Up/Down arrow to ensure visiting all the FOVs");
        logger.info("Verified the NOTE on Slideoverview page");
    }


    //Verify the availability  of roll over zoom on expanded FOV view
    @Test(priority = 65, enabled = true)
    public void verifytherolloverzoominexpandedfovview() throws InterruptedException {
        String checkbox = microscopic.VerifytheROLLOVERZOOMcheckboxinexpandedfovview();
        Assert.assertEquals(checkbox, "Roll over image to Zoom");
        logger.info("Successfully verified theRoll Over Zoom in expanded fov patch view");
    }





    @Test(priority = 67, enabled = true)
    public void verifyfunctionalityofROLLOVERZOOMCHECKBOXmorethanhundredpercent() throws InterruptedException {

        String popup = microscopic.ROLLOVERZOOMCHECKBOXmorethanhundredpercent();
        System.out.println(popup);
        Assert.assertEquals(popup, "Roll over zoom is disabled, FOV is zoomed more than 100x");
    }

    @Test(priority = 68, enabled = false)
    //Verify the functionality of roll over zoom when the image is less than 110% zoom
    public void verifyfunctionalityofROLLOVERZOOMCHECKBOXlessthanhundredpercent() throws InterruptedException {

        String popup = microscopic.verifyfunctionalityofROLLOVERZOOMCHECKBOXmorethanhundredpercent();
        Assert.assertEquals(popup, "Roll over zoom is disabled, FOV is zoomed more than 100x");
    }

    @Test(priority = 69, enabled = true)
    public void verifyImageSizeResetInRolloverzoom() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageSizeReset()) {
            logger.info("The images were reset successfully");
            status = true;
        } else
            logger.info("The images were not reset successfully");
        Assert.assertTrue(status);
    }

    //Verify the zoom in functionality when the roll over zoom is applied.
    @Test(priority = 70, enabled = true)
    public void verifyImageZoomInFUnctionalityinRoLLOVERZOOM() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomIn()) {
            logger.info("The images were zoomed in successfully");
            status = true;
        } else
            logger.info("The images were not zoomed in successfully");
        Assert.assertTrue(status);
    }

    //verify the presence of ROLL OVER ZOOM
    @Test(priority = 71, enabled = true)
    public void verifypresenceofROLLOVERZOOMCHECKBOX() {
        boolean checkbox = microscopic.VerifytheROLLOVERZOOMcheckbox();
        Assert.assertTrue(checkbox);
        logger.info("Successfully verified the checkbox of Roll Over Zoom");
    }


    @Test(priority = 72, enabled = true)
    public void verifyImageZoomOutInRolloverzoom() throws InterruptedException, IOException {
        boolean status = false;
        if (microscopic.verifyImageZoomOut()) {
            logger.info("The images were zoomed out successfully");
            status = true;
        } else
            logger.info("The images were not zoomed out successfully");
        Assert.assertTrue(status);
    }

    @Test(priority = 73, enabled = true)
    public void verifytheecellsofexpandedfovinmicroscopicview(){
      Assert.assertTrue(  microscopic.verifytheecellsofexpandedfovinmicroscopicview());
      logger.info("Succesfully verified the cells of the microscopic view in expanded patch view");
    }



    //Verify the closing of expanded view in Expanded patch view.
    @Test(priority = 74, enabled = true)
    public void verifyclosingEXPANDEDFOV() throws InterruptedException {
        boolean status = microscopic.verifyclosingEXPANDEDFOV();
        Assert.assertTrue(status);
        logger.info(" expanded Microscopic Image is successfully closed ");
    }
}


