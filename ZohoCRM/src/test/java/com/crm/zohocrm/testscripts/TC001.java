package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;

public class TC001 extends BaseTest {
	
	CampaignsListPage campaignsListPage;
	
	@Test(description="To Create A Campain and Verify in Campaign Details Page")
	public void testCampaignIsDisplayed() {
		
		String navTablink = ExcelLibrary.getStringData("TC001", 1, 0);
		String campaignName = ExcelLibrary.getStringData("TC001", 1, 1);
		
		Assert.assertEquals(homePage.verifyTitle(),true,"Home Page Title is Not Correct");
				
		campaignsListPage=(CampaignsListPage) homePage.clickOnNavTabLink(navTablink);
		
		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(),
					true, "All Campaigns Title is not displayed");
		
		campaignsListPage.createCampaign(campaignName);
		
		Assert.assertEquals(campaignsListPage.verifyCampaignDetailsTitle(),
				true, "Campaigns Details Title is not displayed");
		
		homePage.clickOnNavTabLink(navTablink);
		
		Assert.assertEquals(((CampaignsListPage) homePage.clickOnNavTabLink(navTablink)).verifyAllCampaignsTitle(),
					true, "All Campaigns Title is not displayed");
		
		Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName),true,
						"Home Page Title is Not Correct");
		
		
		
		
	}
	
	@AfterMethod
	public void deleteCampaign() {
		Assert.assertEquals(campaignsListPage.deleteCampaign("Nokia"), true);
	}
}
