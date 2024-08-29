package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchedPage {
	
	WebDriver driver;
	
	public SearchedPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[title='Grid view']")
	private WebElement gridviewElement;
	
	@FindBy(css = "[title='List view']")
	private WebElement listviewElement;
	
	@FindBy(css = "[onmouseout=\"this.src='https://www.bookswagon.com/images/buttons/list-view-btn.gif'\"]")
	private WebElement listviewElement2;
	
	@FindBy(css = "[class=\"styled\"]")
	private WebElement shortByElement;
	
	@FindBy(css = "[id=\"ctl00_lblTotalCartItems\"]")
	private WebElement cartElement;
	
	@FindBy(css = "[class=\"wishlisticonheader\"]")
	private WebElement wishlistElement;
	
	@FindBy(xpath = "(//input[@value=\"Add to cart\"])[1]")
	private WebElement addtocartButtonElement;
	
	@FindBy(xpath = "(//input[@value=\"Add to Wishlist\"])[1]")
	private WebElement wishlistButtonElement;
	
	@FindBy(css = "[href=\"https://www.bookswagon.com/search-books/chetan-half/title?sid=0&tab=2\"]")
	private WebElement byTitlElement;
	
	@FindBy(partialLinkText = "Rs.100 - Rs.500")
	private WebElement byPricElement;
	
	@FindBy(partialLinkText = "0% - 10%")
	private WebElement byDiscountElement;
	
	@FindBy(partialLinkText = "In Stock")
	private WebElement availability;
	
	@FindBy(partialLinkText = "English")
	private WebElement language;
	
	@FindBy(css = "[href=\"javascript:GetSearchCriteria('books','Source:1');\"]")
	private WebElement sourcElement;
	
	@FindBy(css = "[href=\"javascript:GetSearchCriteria('books','Binding:1');\"]")
	private WebElement bindingElement;

	public WebElement getSourcElement() {
		return sourcElement;
	}

	public WebElement getBindingElement() {
		return bindingElement;
	}

	public WebElement getGridviewElement() {
		return gridviewElement;
	}

	public WebElement getListviewElement() {
		return listviewElement;
	}
	

	public WebElement getListviewElement2() {
		return listviewElement2;
	}

	public WebElement getShortByElement() {
		return shortByElement;
	}

	public WebElement getCartElement() {
		return cartElement;
	}

	public WebElement getWishlistElement() {
		return wishlistElement;
	}

	public WebElement getAddtocartButtonElement() {
		return addtocartButtonElement;
	}

	public WebElement getWishlistButtonElement() {
		return wishlistButtonElement;
	}

	public WebElement getByTitlElement() {
		return byTitlElement;
	}

	public WebElement getByPricElement() {
		return byPricElement;
	}

	public WebElement getByDiscountElement() {
		return byDiscountElement;
	}

	public WebElement getAvailability() {
		return availability;
	}

	public WebElement getLanguage() {
		return language;
	}
	
}
