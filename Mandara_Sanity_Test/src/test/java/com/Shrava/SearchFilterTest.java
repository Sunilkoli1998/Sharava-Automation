package com.Shrava;

import com.Shrava_sanity.Comment;
import com.Shrava_sanity.ListReport;
import com.Shrava_sanity.ReportPage;
import com.Shrava_sanity.SearchFilter;
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

import java.util.*;
import java.util.concurrent.TimeUnit;


public class SearchFilterTest extends BrowserSetUp {

	public Properties props;
	public SearchFilter searchfilter;
	private final Logger logger = LogManager.getLogger(SearchFilterTest.class);


	@BeforeSuite
	public void setUp() throws Exception {
		driver = getDriver();
		searchfilter = new SearchFilter(driver);
		//PropertiesFile.readSearchFilterFile();
		props = PropertiesFile.prop;
	}

	// Verify the Filter icon is present and clickable
	@Test(priority = 1, enabled = true)
	public void searchfilterPresentTest() {
		test = extent.createTest("Search Filter Test");
		boolean searchfilterPresent = searchfilter.searchfilterPresent();
		Assert.assertTrue(searchfilterPresent);
		logger.info("Search filter is enabled");
	}

	// Verify filter box opens successfully on clicking the icon
	@Test(priority = 2, enabled = true)
	public void openFilterboxTest() throws InterruptedException {
		boolean openFilterbox = searchfilter.openFilterbox();
		Assert.assertTrue(openFilterbox);
		logger.info("Search modal window is opened successfully");
	}

	// Verify the default values of all the fields
	@Test(priority = 3, enabled = true)
	public void defaultFieldValuesTest() throws InterruptedException {
		int a = searchfilter.defaultFieldValues();
		//System.out.println(a);
		if (a == 7) {
			logger.info("All fields are present and set to their default value");
		} else {
			logger.info("Fields needs to be reset back");
		}
	}

	// Verify the Default Field Names present in the Filter Box
	@Test(priority = 4, enabled = false)
	public void defaultFieldNameTest() {
		boolean flag = searchfilter.validateDefaultFieldNames();
		Assert.assertTrue(flag);
		logger.info("The Default names have been verified");

	}

	// Verify the default values of all the date fields
	@Test(priority = 5, enabled = true)
	public void defaultDateFieldTest() {
		int a = searchfilter.defaultDateFields();
		// System.out.println(a);
		if (a == 2) {
			logger.info("All the Date fields are present and set to their default value");
		} else {
			logger.info("Fields needs to be reset back");
		}

	}

	// Verify Cancel, Reset and Apply buttons are enabled and clickable
	@Test(priority = 6, enabled = true)
	public void modalfooterTest() {
		boolean cancel = searchfilter.cancelButton();
		boolean reset = searchfilter.resetButton();
		boolean apply = searchfilter.applyButton();

		Assert.assertTrue(cancel);
		Assert.assertTrue(reset);
		Assert.assertTrue(apply);

		logger.info("Cancel, Reset and Apply buttons are enabled to be clicked.");
	}

	// Verify all the fields reset back
	@Test(priority = 7, enabled = true)
	public void clickReset() throws InterruptedException {
		searchfilter.clickReset();
		int a = searchfilter.defaultFieldValues();
		a += searchfilter.defaultDateFields();

		if (a == 8) {
			logger.info("All fields are set to default values again");
		} else {
			logger.info("Fields needs to be reset back");
		}
		//Thread.sleep(1000);

	}

	// verification on clicking Cancel button
	@Test(priority = 8, enabled = true)
	public void clickCancelTest() throws InterruptedException {
		Assert.assertTrue(searchfilter.clickCancel());
		logger.info("On clicking Cancel button, the screen gets redirected to the List Page by default");
	}


	// verification on clicking Apply button
	@Test(priority = 9, enabled = true)
	public void clickApplyTest() throws InterruptedException {
		searchfilter.openFilterbox();
		Assert.assertTrue(searchfilter.clickApply());
		logger.info("On clicking Apply button, the screen gets redirected to the List Page by default");
	}

	// Verification of options after selecting Branch, installation
	@Parameters({"product"})
	@Test(priority = 10, enabled = false)
	public void selectByInstallationNameTest(String product) throws InterruptedException {

		boolean flag1 = searchfilter.selectInstallationName("Shonit");
		boolean flag2 = false;

		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and Installation ID");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Installation filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and Installation ID is wrong");
			}
		}

	}


	// Verifying the search by selected reviewer
	@Test(priority = 10, enabled = true)
	public void verifyInstallationID() throws InterruptedException {
		Thread.sleep(3000);
		String installation=searchfilter.installationId();
		Assert.assertEquals(installation,"SIG-Sig-1165-1");
		logger.info("The actual installation id is "+installation);
	}




	// Verifying the search by selected reviewer
	@Test(priority = 11, enabled = true)
	public void selectByReviewerTest() throws InterruptedException {
		boolean flag1 = searchfilter.selectByReviewer();
		boolean flag2 = false;

		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and Reviewer name ");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Revierwer filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and Reviewer name is wrong");
			}
		}

	}

	// Verifying the search by review status for Approved
	@Test(priority = 21, enabled = true)
	public void selectByReviewStatusForApproveTest() throws InterruptedException {
		Thread.sleep(4000);
		boolean flag1 = searchfilter.selectByReviewStatusApporved();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and Approved Status");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Approved Status filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and Approved Status is wrong");
			}
		}
	}

	// Verifying Filter Result Consistency
	@Test(priority = 42, enabled =  true)
	public void verifyFilterResultConsistencyTest() throws InterruptedException {
		boolean flag = searchfilter.checkFilterResultConsistency();
		Assert.assertTrue(flag);
		logger.info("List Report Result is not changing after re-entering and cancelling the filter.");
	}
	// Verifying Filter Result Consistency
	@Test(priority = 20, enabled = false)
	public void verifyFilterDataTest() throws InterruptedException {
		boolean flag = searchfilter.checkFilterBoxData();

		Assert.assertTrue(flag);
		logger.info("Applied Filter Values staying same after opening the Filter again. ");
	}
	// Verifying the search by review false for  Rejected
	@Test(priority = 25, enabled = true)
	public void selectByReviewStatusForRejectTest() throws InterruptedException {
		boolean flag1 = searchfilter.selectByReviewStatusRejected();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and Rejected Status");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Rejected Status filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and Rejected Status is wrong");
			}
		}
	}

	// Verifying the unassigned reviewer status
	@Test(priority = 30, enabled = true)
	public void selectUnassignedReviewTest() throws InterruptedException {
		boolean flag1 = searchfilter.selectUnassignedReview();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and Unassigned Status");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Unassigned Status filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and Unassigned Status is wrong");
			}
		}
	}

	// Verifying the Assigned reviewer status
	@Test(priority = 35, enabled = true)
	public void selectAssignedReviewTest() throws InterruptedException {
		boolean flag1 = searchfilter.selectAssignedReview();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected branch and assigned Status");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied assigned Status filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected branch and assigned Status is wrong");
			}
		}
	}

	// Verifying multiple filtewr results, in this case branch, installation name
	// and reviewer
	@Test(priority = 40, enabled = true)
	public void selectFiltersForReviewerAndStatusTest() throws InterruptedException {
		boolean flag1 = searchfilter.filterByReviewerAndReviewStatus();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for selected Reviewer and Approved Status");

		}
		else {
			flag2 = searchfilter.noDataFound();
			if(flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the applied Reviewer and Approved Status filter");

			}
			else {
				Assert.assertTrue(flag1);
				logger.info("The search result for selected Reviewer and Approved Status is wrong");
			}
		}
	}

	// Verifying filter result for Installtion,Reviewer and Review Status filter.
	@Parameters({"product"})
	@Test(priority = 45, enabled = false)
	public void applyMultipleFiltersTest(String product) throws InterruptedException {
		boolean flag1 = searchfilter.applyMultiPleFilters("Shonit");
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search is successful for multiple filters");

		} else {
			flag2 = searchfilter.noDataFound();
			if (flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the multiple filters");

			} else {
				Assert.assertFalse(flag1);
				logger.info("The search result for multiple filters is wrong");
			}
		}}


	// Verifying search by date Range
	@Test(priority = 50, enabled = false)
	public void selectByDateFilterTest() throws InterruptedException {
		boolean flag1 = searchfilter.selectByDateFilter();
		boolean flag2 = false;
		if (flag1) {
			Assert.assertTrue(flag1);
			logger.info("The search result is successful for the given date range");

		} else {
			flag2 = searchfilter.noDataFound();
			if (flag2) {
				Assert.assertTrue(flag2);
				logger.info("There is no data found for the Seleted Date Range.");

			} else {
				Assert.assertTrue(flag1);
				logger.info("The search result for Seleted Date Range is wrong");
			}
		}


	}
}




