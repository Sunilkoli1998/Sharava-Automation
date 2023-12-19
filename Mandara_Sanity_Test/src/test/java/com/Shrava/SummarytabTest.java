package com.Shrava;

import com.Shrava_sanity.Comment;
import com.Shrava_sanity.ListReport;
import com.Shrava_sanity.ReportPage;
import com.Shrava_sanity.Summarytab;
import com.data.Shrava_data.PropertiesFile;
import com.utilities.BrowserSetUp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SummarytabTest extends BrowserSetUp {

    public static Properties props;
    public Summarytab summary ;
    private final Logger logger = LogManager.getLogger(SummarytabTest.class);
    public BrowserSetUp pageload;
    public ListReport listreport;
    public ReportPage reportpage;
    private Comment comment;



    @BeforeSuite
    public void setUp() throws Exception {
        driver = getDriver();
        reportpage=new ReportPage(driver);
        summary = new Summarytab(driver);
        comment=new Comment(driver);
        props = PropertiesFile.prop;
    }

//--------------------------- List Report Page ----------------------------------
@Test(priority = 1, enabled = true)
    public void searchsampleID() throws InterruptedException {
        boolean samplestatus = reportpage.searchSampelID();
        test = extent.createTest("Report Page Test");
        Assert.assertTrue(samplestatus);
        logger.info("sample is searched for get the reprot for page");
    }

    @Parameters({"product"})
    @Test(priority = 2, enabled = true)
    public void verifythepresenceofcellsinthesummarytabofUrineReport() throws InterruptedException {
        test = extent.createTest("SummaryTab Test");
        Assert.assertTrue(summary.verifythecellsinthesummarytab());
        logger.info("Successfully verified the all the cells in the summary tab of urine report");
    }

    @Test(priority = 3, enabled = true)
    public void Verifythecolumnspresentforthesummarytab(){
        Assert.assertTrue(summary.Verifythecolumnspresentforthesummarytab());
        logger.info("Successfully verified the all the columns in the summary tab of urine report");

    }
    @Test(priority = 4, enabled = true)
    public void verifytheavailabilityofthecrystalcellandsubcellsofcrystal(){
        Assert.assertTrue(summary.verifytheavailabilityofthecrystalcellandsubcellsofcrystal());
        logger.info("Successfully verified the Crystall cells of urine report");

    }

    @Test(priority = 5, enabled = true)
  public void  Verifytheresulttypementionedforthecrystalcelltypeinthesummarytab(){
        Assert.assertTrue(summary.Verifytheresulttypementionedforthecrystalcelltypeinthesummarytab());
        logger.info("Successfully verified the Verify the result type mentioned for the crystal cell type in the summary tab");
    }




    @Test(priority = 6, enabled = true)
    public void verifyfunctionalityOfEditButton() throws InterruptedException {
            Assert.assertTrue(summary.verifythefunctionalityoftheeditbutton());
            logger.info("Successfully verified the updated results value");
    }


    @Test(priority = 7,enabled = true)
    public void  verifytheGradeofResultss() throws InterruptedException {
        Assert.assertTrue(summary. verifythecountsandGradeofResults());
        logger.info("Successfully verified the Results and Grade of the cells");

    }

    @Test(priority = 8  ,enabled = true)
    public void verifyingtheresultofcrystalcell() throws InterruptedException {
        Assert.assertTrue(summary.verifyingtheresultofcrystalcell());
        logger.info("Successfully the verified the result of crystal cell");

    }

    @Test(priority = 9  ,enabled = true)
    public void verifytheresultschangingfromNDtoD() throws InterruptedException {
        Assert.assertTrue(summary.changintheresultsfromDtoNDorNDtoD());
        logger.info("Successfully verified the original results and updated reults in the update popup ");

    }

//verifying the original results values are not changing when we change the original value and not confirming in the updatepoup means original results should remain same
@Test(priority = 10  ,enabled = true)
    public void verifyingtheoriginalresultsvaluesarenotchnagingwithoutconfirmingtheupdatepopup() throws InterruptedException {
        Assert.assertTrue(summary.verifyingtheoriginalresultsvaluesarenotchnagingwithoutconfirmingtheupdatepopup());
        logger.info("Successfully verifyied the original results values are not changing when we change the original value and not confirming in the updatepoup means original results are same ");

    }

    @Test(priority = 11 ,enabled = false)
    public void Verificationofthehidingfunctionalityofthecrystalsubclasses(){
        Assert.assertTrue(summary.Verificationofthehidingfunctionalityofthecrystalsubclasses());
        logger.info("Suceessfully verified the expanding functionality of the crystal subclasses by clicking on the - button.");
    }





}

