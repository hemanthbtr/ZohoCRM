package com.crm.zohocrm.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.crm.zohocrm.generic.IAutoConstants;
import com.crm.zohocrm.generic.Utility;
import com.crm.zohocrm.generic.WebActionUtil;
import com.crm.zohocrm.pom.HomePage;
import com.crm.zohocrm.pom.LoginPage;

public class BaseTest implements IAutoConstants {
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public HomePage homePage;
	@Parameters({"browser","appUrl","implicit","explicit"})
	@BeforeClass(alwaysRun=true)
	public void openApp(@Optional(DEFAULT_BROWSER)String browser,
						@Optional(APP_URL)String appUrl,
						@Optional(ITO)String implict,
						@Optional(ETO)String explicit) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty(CHROME_KEY, CHROME_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty(GECKO_KEY, GECKO_PATH);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled",false);
			driver = new FirefoxDriver(options);
		} else {
			Assert.fail("Zoho Supports only chrome and Firefox");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(implict), TimeUnit.SECONDS);
		driver.get(appUrl);
		webActionUtil = new WebActionUtil(driver, Long.parseLong(explicit));
	}
	
	@Parameters({"user","pass"})
	@BeforeMethod(alwaysRun=true)
	public void loginToApp(@Optional(DEFAULT_USERNAME)String user,
						   @Optional(DEFAULT_PASSWORD) String pass) {
		LoginPage loginPage = new LoginPage(driver, webActionUtil);
		homePage = loginPage.signIn(user, pass);
	}
	
	@AfterMethod(alwaysRun=true)
	public void logoutFromApp(ITestResult result) {
		 if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println(Utility.getImage(driver, result.getName()));
		}
					
		homePage.logout();
	}
	
	@AfterClass(alwaysRun=true)
	public void closeApp() {
		driver.quit();
	}
	
	@FindBy(id="username")private WebElement ele;
}
