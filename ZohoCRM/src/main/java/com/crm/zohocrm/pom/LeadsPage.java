package com.crm.zohocrm.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.zohocrm.generic.WebActionUtil;

public class LeadsPage extends BasePage {

	final static String campaignLookUpPageTitle = "Zoho CRM - Campaign Name Lookup";
	
	@FindBy(css=".title.hline")
	private WebElement leadsTitle;
	
	@FindBy(xpath="//input[@value='New Lead']")
	private WebElement newLeadButton;
	
	@FindBy(xpath="//img[@title='Campaign Name Lookup']")
	private WebElement campaigLookUpIcon;
	
	@FindBy(className="tableData")
	private WebElement firstCampaignNameInLookUp;
	
	
	public WebElement getLeadsTitle() {
		return leadsTitle;
	}

	public WebElement getCampaigLookUpIcon() {
		return campaigLookUpIcon;
	}

	public WebElement getNewLeadButton() {
		return newLeadButton;
	}

	public WebElement getLeadsPageTitle() {
		return leadsTitle;
	}

	public LeadsPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}

	public boolean verifyLeadsPageIsDisplayed() {
		return leadsTitle.getText().contains("All Open Leads");
	}
	
	public boolean verifyCreateLeadsPageIsDisplayed() {
		return leadsTitle.getText().contains("Create Lead");
	}
	
	public boolean verifyCampaignLookUpPageIsDisplayed() {
		return webActionUtil.switchToWindow(campaignLookUpPageTitle);
	}
	
	public void closeCampaignLookUpPage() {
		webActionUtil.closeWindow(campaignLookUpPageTitle);
		webActionUtil.switchToParentWindow();
	}
	
	public boolean verifyCampaginIsDisplayedCampaignLookUpPage(String campignName) {
		return firstCampaignNameInLookUp.getText().equals(campignName);
	}

}
