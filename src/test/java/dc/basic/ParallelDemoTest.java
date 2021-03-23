package dc.basic;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelDemoTest {
	public WebDriver driver;
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws MalformedURLException {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver();
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			dc.setPlatform(Platform.ANY);
			dc.setVersion("");
			System.out.println("Running the sample file in Docker <<Selenium standalone Chrome>> container");
			driver= new RemoteWebDriver(new URL("http://192.168.0.104:4446/wd/hub"),dc);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new ChromeDriver();
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setPlatform(Platform.ANY);
			dc.setVersion("");
			System.out.println("Running the sample file in Docker <<Selenium standalone firefox>> container");
			driver= new RemoteWebDriver(new URL("http://192.168.0.104:4445/wd/hub"),dc);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		
		

	}
	
	@Test
	public void demoTest() throws InterruptedException {
		
		System.out.println("Demo Test");
		driver.get("https://www.youtube.com/");
		Thread.sleep(3000);
		String expTitle = driver.getTitle();
		System.out.println(expTitle);
		System.out.println("POC- Jenkins || Docker  || Github Integration");
		driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys("Fire on Fire Sam Smith");
		driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]/yt-icon")).click();
		Thread.sleep(20000);
	}
	
	@Test
	public void demoTestInsta() throws InterruptedException {
		System.out.println("Demo Test");
		driver.get("https://www.instagram.com/");
		Thread.sleep(3000);
		String expTitle = driver.getTitle();
		System.out.println(expTitle);
		System.out.println("POC- Jenkins || Docker  || Github Integration");
		//driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys("Fire on Fire Sam Smith");
		//driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]/yt-icon")).click();
		Thread.sleep(20000);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
