package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
public static WebDriver driver;
public static Properties config;
@BeforeSuite
public static void setUp()throws Throwable
{
	config =new Properties();
	config.load(new FileInputStream("C:\\MyProject1\\Hybrid_FrameWork\\PropertyFiles\\Environment.Properties"));
	if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "C:\\MyProject1\\Hybrid_FrameWork\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
		Thread.sleep(3000);
	}
	else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
		Thread.sleep(3000);
	}
	else
	{
		System.out.println("browser is not matching");
	}
}
@AfterSuite
public static void tearDown()
{
	driver.close();
}
}
