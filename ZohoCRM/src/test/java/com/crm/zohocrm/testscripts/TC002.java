package com.crm.zohocrm.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.crm.zohocrm.generic.ExcelLibrary;
import com.crm.zohocrm.pom.CampaignsListPage;
import com.crm.zohocrm.pom.LeadsPage;

public class TC002 extends BaseTest {
	
	CampaignsListPage campaignsListPage;
	String campaignName;
	String navTablink1;
	
	@Test(description="To Create A Campain and Verify in Campaign Details Page")
	public void testCampaignIsDisplayed() {
		
		navTablink1 = ExcelLibrary.getStringData("TC002", 1, 0);
		campaignName = ExcelLibrary.getStringData("TC002", 1, 1);
		String navTablink2 = ExcelLibrary.getStringData("TC002", 1, 2);
		
		Assert.assertEquals(homePage.verifyTitle(),true,"Home Page Title is Not Correct");
				
		campaignsListPage=(CampaignsListPage) homePage.clickOnNavTabLink(navTablink1);
		
		Assert.assertEquals(campaignsListPage.verifyAllCampaignsTitle(),
					true, "All Campaigns Title is not displayed");
		
		campaignsListPage.createCampaign(campaignName);
		
		Assert.assertEquals(campaignsListPage.verifyCampaignDetailsTitle(),
				true, "Campaigns Details Title is not displayed");
		
		homePage.clickOnNavTabLink(navTablink1);
		
		Assert.assertEquals(((CampaignsListPage) homePage.clickOnNavTabLink(navTablink1))
					.verifyAllCampaignsTitle(), true, "All Campaigns Title is not displayed");
		
		Assert.assertEquals(campaignsListPage.verifyCampaign(campaignName),true,
						"Home Page Title is Not Correct");
		
		
		LeadsPage leadsPage = (LeadsPage) homePage.clickOnNavTabLink(navTablink2);
		Assert.assertEquals(leadsPage.verifyLeadsPageIsDisplayed(), 
											true,"Leads Page is Not DIsplayed");
		
		leadsPage.getNewLeadButton().click();
		
		Assert.assertEquals(leadsPage.verifyCreateLeadsPageIsDisplayed(), true,
													"Create Lead Page is Not DIsplayed");
		
		leadsPage.getCampaigLookUpIcon().click();
		
		Assert.assertEquals(leadsPage.verifyCampaignLookUpPageIsDisplayed(), 
										true,"Campaign Lookup Page is Not DIsplayed");
		
		Assert.assertEquals(leadsPage.verifyCampaginIsDisplayedCampaignLookUpPage(campaignName), 
				true,"Campaign Lookup Page is Not DIsplayed");
		
		leadsPage.closeCampaignLookUpPage();
	}
	
	@AfterMethod
	public void deleteCampaign() {
		homePage.clickOnNavTabLink(navTablink1);
		Assert.assertEquals(campaignsListPage.deleteCampaign(campaignName), true);
	}
}
