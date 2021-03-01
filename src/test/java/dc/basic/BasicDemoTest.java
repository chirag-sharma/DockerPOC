package dc.basic;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicDemoTest {
	static RemoteWebDriver driver;
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		System.out.println("Running the sample file in Docker <<Selenium standalone Chrome>> container");
		
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setPlatform(Platform.LINUX);
		dc.setVersion("");
		
		driver= new RemoteWebDriver(new URL("http://192.168.43.128:4444/wd/hub"),dc);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void demoTest() throws InterruptedException {
		System.out.println("Demo Test");
		driver.get("https://www.youtube.com/");
		Thread.sleep(20000);
		String expTitle = driver.getTitle();
		System.out.println(expTitle);
		System.out.println("POC- Jenkins || Docker  || Github Integration");
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
