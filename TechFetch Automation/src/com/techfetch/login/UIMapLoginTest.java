package com.techfetch.login;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
public class UIMapLoginTest {
	public WebDriver driver;
	public UIMap uimap;
	public UIMap datafile;
	public String workingDir;
	
	@Test
	public void login() throws Exception {
 
		// Get object map file
		uimap = new UIMap(workingDir + "\\Resources\\locator.properties");
 
		// Get the username element
		WebElement txtemailid = driver.findElement(uimap.getLocator("Username_field"));
		
		txtemailid.sendKeys(datafile.getData("txtemailid"));
 
		// Get the password element
		WebElement txtpwd = driver.findElement(uimap.getLocator("Password_field"));
		
		txtpwd.sendKeys(datafile.getData("txtpwd"));
 
		// Click on the Login button
		WebElement btnSubmit = driver.findElement(uimap.getLocator("Login_button"));
		btnSubmit.click();
		
 
		Thread.sleep(1000);
		
		// Assert the user login by checking the Online user
		/*WebElement onlineuser = driver.findElement(uimap
				.getLocator("online_user"));
		assertEquals("Hi, John Smith", onlineuser.getText());*/
	}
 
	@BeforeMethod
	public void setUp() throws Exception {
 
		// Get current working directory and load data file
		workingDir = System.getProperty("user.dir");
		datafile = new UIMap(workingDir + "\\Resources\\data.properties");
 
		// Create a new instance of the Firefox driver
		 System.setProperty("webdriver.firefox.driver", "C:\\Users\\subu\\Documents\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://uat.techfetch.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='ucHeaderCtrl_divCandidate']/a")).click();
		driver.findElement(By.xpath(".//*[@id='jsloginpop']")).click();
		driver.switchTo().frame("candidatecontentframe");
		
		
	}
 
	@AfterMethod
	public void afterMethod() throws Exception {
		driver = new FirefoxDriver();
		driver.switchTo().frame("contentframe");
		driver.switchTo().frame("replacedoccontentframe");
		driver.findElement(By.xpath(".//*[@id='ucMoreResumes_lblJobs']/table/tbody/tr[1]/td[4]")).click();
		
	}
}
