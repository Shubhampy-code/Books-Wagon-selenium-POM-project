package generics;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	protected WebDriver driver;
	String url;
	protected String book1;
	protected String invalidName;
	protected String writerName;
	protected String mixInput;
	protected String book2;
	
	
	
	@BeforeMethod
	public void setup() throws IOException {
		
		driver = new ChromeDriver();
		
		Properties properties = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Shubham Shrivastava\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\bookswagon_POM\\src\\test\\resources\\config.properties");
		properties.load(fis);
		
		url = properties.getProperty("url");
		book1 = properties.getProperty("book1");
		invalidName = properties.getProperty("invalidName");
		writerName = properties.getProperty("writerName");
		mixInput = properties.getProperty("mixInput");
		book2 = properties.getProperty("book2");
		
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
