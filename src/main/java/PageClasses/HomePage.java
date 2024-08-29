package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "[class=\"inputbar\"]")
	private WebElement searchBar;
	
	@FindBy(css = "[name=\"btnTopSearch\"]")
	private WebElement searchButton;

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	//Buisness method
	public void search(String book1) {
		this.searchBar.sendKeys(book1);
		//searchButton.click();
		
	}
	
	
}
