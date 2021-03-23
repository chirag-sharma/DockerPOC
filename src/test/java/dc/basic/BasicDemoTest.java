package dc.basic;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicDemoTest {
	public RemoteWebDriver driver;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		System.out.println("Running the sample file in Docker <<Selenium standalone Chrome>> container");
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setPlatform(Platform.ANY);
		dc.setVersion("");
		
		driver= new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"),dc); //amazon.ec2.aws:4444

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test
	public void demoTest() throws InterruptedException {
		System.out.println("The thread ID for Youtube Test is "+ Thread.currentThread().getId());
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
		System.out.println("The thread ID for Insta Test is "+ Thread.currentThread().getId());
		System.out.println("Demo Test Insta");
		driver.get("https://www.instagram.com/");
		Thread.sleep(3000);
		String expTitle = driver.getTitle();
		System.out.println(expTitle);
		System.out.println("POC- Jenkins || Docker  || Github Integration");
		//driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys("Fire on Fire Sam Smith");
		//driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]/yt-icon")).click();
		//Thread.sleep(20000);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
