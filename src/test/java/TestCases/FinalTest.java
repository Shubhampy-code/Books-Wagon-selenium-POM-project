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

import com.relevantcodes.extentreports.LogStatus;

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
		Test.log(LogStatus.PASS, "Search the 'the hidden hindu - Books' ");
		
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
		Test.log(LogStatus.PASS, "Changing into grid view");

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
		
		Test.log(LogStatus.PASS, "Changing into list view");
		
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
		
		Test.log(LogStatus.PASS, "Add to cart the searched book");

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
		
		Test.log(LogStatus.PASS, "Add to wishlist");

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
		
		Test.log(LogStatus.PASS, "Short by the search");

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
		
		Test.log(LogStatus.PASS, "Giving the invaild input in the search box");

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
		
		Test.log(LogStatus.PASS, "Search the book by the writter's name");

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
		
		Test.log(LogStatus.PASS, "Clear the search box field");

	}
	
	
	// Verify the search functionality without giving any input.
	
	@Test
	public void EmptyInput() throws IOException {
		hPage = new HomePage(driver);
		hPage.getSearchButton().sendKeys(" ");
		hPage.getSearchButton();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		capture(driver, 10);
		
		Test.log(LogStatus.PASS, "Empty input in search box");

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
		
		Test.log(LogStatus.PASS, "Giving the mixInput in the search box");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the title");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the price");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the discount");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the availability");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the language");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the source");

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
		
		Test.log(LogStatus.PASS, "Refine the search by the binding");

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
		
		Test.log(LogStatus.PASS, "Check the 404 invalid page 'Go Back button'");

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
		
		Test.log(LogStatus.PASS, "End to End testing");

		
	}
	
}
