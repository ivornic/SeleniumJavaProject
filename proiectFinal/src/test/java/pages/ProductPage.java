package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class ProductPage extends PageBase {
    @FindBy (tagName = "h1")
    private WebElement productTitle;
    @FindBy (id = "short_description_content")
    private WebElement productDescription;
    @FindBy (id = "quantity_wanted")
    private WebElement productQuantityField;
    @FindBy (id = "group_1")
    private WebElement productSizeField;
    @FindBy (name = "Submit")
    private WebElement addToCartButton;
    @FindBy (xpath = "//*[contains(@class,'button button-medium')]")
    private WebElement proceedCheckoutButton;
    @FindBy (xpath = "//*[contains(@class,'continue')]")
    private WebElement continueSoppingButton;


    public ProductPage ( WebDriver driver ) {
        super ( driver );
    }

    public String getTitle ( ) {
        waitForWebElementToBeVisible ( productTitle );
        return productTitle.getText ( );
    }

    public String getDescription ( ) {
        waitForWebElementToBeVisible ( productDescription );
        return productDescription.getText ( );
    }

    public void clickOnProceeedToCheckOut ( ) {
        longWaitForWebElementToBeVisible ( proceedCheckoutButton );
        proceedCheckoutButton.click ( );
    }

    public void addProductToCart ( ) {
        longWaitForWebElementToBeVisible ( addToCartButton );
        addToCartButton.click ( );
    }


    public ShoppingCartPage submitAndCheckout ( ) {
        addProductToCart ( );
        Actions action = new Actions ( driver );
        action.moveToElement ( proceedCheckoutButton );
        clickOnProceeedToCheckOut ( );
        return new ShoppingCartPage ( driver );

    }

    public void clickOnContinueButton ( ) {
        longWaitForWebElementToBeVisible ( continueSoppingButton );
        continueSoppingButton.click ( );
    }

    public void addProductToCartAndContinue ( ) {
        addProductToCart ( );
        clickOnContinueButton ( );

    }


}
