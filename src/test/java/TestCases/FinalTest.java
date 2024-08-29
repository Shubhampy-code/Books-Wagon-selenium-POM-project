package TestCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageClasses.HomePage;
import PageClasses.SearchedPage;
import generics.BaseTest;

public class FinalTest extends BaseTest{
	
	HomePage hPage;
	SearchedPage sPage;
	
	public static void capture(WebDriver driver, int n) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		//using getscreenshotAs() to take screenshot

		File image = ts.getScreenshotAs(OutputType.FILE);
		
		File img = new File("C:\\Users\\Shubham Shrivastava\\Pictures\\Screenshots\\image"+n+".png");
		
		FileUtils.copyFile(image, img); 
	}
	
	
	// In the searchBook we are testing the basic search functionality and assert the search result and take a screenshot. 
	
	@Test
	public void searchBook() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.getTitle(), "the hidden hindu - Books - 24x7 online bookstore Bookswagon.com");
		
		capture(driver, 1);
	}
	
	
	//     Checking the grid view functionality.
	
	@Test
	public void gridView() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		sPage = new SearchedPage(driver);
		sPage.getGridviewElement().click();
		
		capture(driver, 2);
	}
	
	
	// Verify the list view functionality
	
	@Test
	public void listView() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		sPage = new SearchedPage(driver);
		sPage.getGridviewElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		sPage.getListviewElement2().click();
		
		capture(driver, 3);
		
	}
	
	
	// verify the add to cart button 
	
	@Test
	public void addToCart() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		sPage = new SearchedPage(driver);
		sPage.getAddtocartButtonElement().click();
		sPage.getCartElement().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.cssSelector("[id=\"ctl00_phBody_BookCart_lvCart_ctrl0_lblProductTitle\"]")), "The Hidden Hindu");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 4);
	}
	
	
	// Verify the Wishlist button.
	
	@Test
	public void addToWishlist() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		sPage = new SearchedPage(driver);
		sPage.getWishlistButtonElement().click();
		
		capture(driver, 5);
	}
	
	
	// verify the short-by functionality
	
	@Test
	public void shortby() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book1);
		hPage.getSearchButton().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		sPage = new SearchedPage(driver);
		WebElement dropdown = sPage.getShortByElement();
		Select s = new Select(dropdown);
		s.selectByVisibleText("Discount - High to Low");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 6);
	}
	
	
	// verify the search functionality by giving the invalid input.
	
	@Test
	public void InvalidInput() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(invalidName);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("//table/tbody/tr/td/h4")), "404 Page Not Found");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 7);
	}
	
	
	// Verify the search functionality by giving the writter name. 

	@Test
	public void searchByWriter() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(writerName);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("//div[@class=\"search-summary search-heading\"]/h1/span")), "chetan bhagat");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 8);
	}
	
	
	// clear the search box 
	
	@Test
	public void clearSearchBox() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(writerName);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("//div[@class=\"search-summary search-heading\"]/h1/span")), "chetan bhagat");
		hPage.getSearchBar().clear();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 9);
	}
	
	
	// Verify the search functionality without giving any input.
	
	@Test
	public void EmptyInput() throws IOException {
		hPage = new HomePage(driver);
		hPage.getSearchButton().sendKeys(" ");
		hPage.getSearchButton();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 10);
	}
	
	
	// Verify the seachh functionality by giving the mix input (Ex --> Chetan half).
	
	@Test
	public void mixInput() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");

		capture(driver, 11);
	}
	
	
	// verify the filter functionality by the refineByTitle.
	
	@Test
	public void refineByTitle() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getByTitlElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 12);
	}
	
	
	// verify the filter functionality by the refineByPrice.
	
	@Test
	public void refineByPrice() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getByPricElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 13);
	}
	
	
	// verify the filter functionality by the refineByDiscount.
	
	@Test
	public void refineByDiscount() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getByDiscountElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 14);
	}
	
	
	// verify the filter functionality by the refineByAvailability.
	
	@Test
	public void refineByAvailability() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getAvailability().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 15);
	}
	
	
	// verify the filter functionality by the refineByLanguage.
	
	@Test
	public void refineByLanguage() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getLanguage().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 16);
	}
	
	
	// verify the filter functionality by the refineBySource.
	
	@Test
	public void refineBySource() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getSourcElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 17);
	}
	
	
	// verify the filter functionality by the refineByBinding.
	
	@Test
	public void refineBybinding() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(mixInput);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/half-girlfriend-chetan-bhagat/9788129135728\"])[2]")), "Half Girlfriend");
		
		sPage =new SearchedPage(driver);
		sPage.getBindingElement().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		capture(driver, 18);
	}
	
	
	// giving invalid input and check go back button.
	
	@Test
	public void InvaidPage() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(invalidName);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("//table/tbody/tr/td/h4")), "404 Page Not Found");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.cssSelector("[value=\"Go Back\"]")).click();
	}
	
	
	// End to end testing
	
	@Test
	public void endToEndTest() throws IOException {
		hPage = new HomePage(driver);
		hPage.search(book2);
		hPage.getSearchButton().click();
		
		SoftAssert as = new SoftAssert();
		as.assertEquals(driver.findElements(By.xpath("(//a[@href=\"https://www.bookswagon.com/book/secret-nagas-shiva-trilogy-book/9789356290600\"])[2]")), "The Secret Of The Nagas (Shiva Trilogy Book 2)");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.cssSelector("[onclick=\"AddtoCart('36476836',1,'63',1)\"]")).click();
		
		sPage = new SearchedPage(driver);
		sPage.getCartElement().click();
		
		capture(driver, 19);
		
		driver.findElement(By.cssSelector("[id=\"ctl00_phBody_BookCart_lvCart_ctrl0_imgDelete\"]")).click();
		
		capture(driver, 20);
		
	}
	
}
