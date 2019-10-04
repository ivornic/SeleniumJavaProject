package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

import java.util.List;

public class ShoppingCartPage extends PageBase {
    @FindBy ( xpath = "//*[contains(@class,'standard-checkout')]" )
    private WebElement        checkoutButton;
    @FindBy ( className = "cart_quantity_delete" )
    private List <WebElement> deleteButton;
    @FindBy ( xpath = "//*[contains(@class,'alert-warning')]" )
    private WebElement        allertMessage;
    @FindBy ( id = "total_price" )
    private WebElement        totalPriceInCart;


    public ShoppingCartPage ( WebDriver driver ) {
        super ( driver );
    }

    public LoginPage clickOnCheckoutButton ( ) {
        longWaitForWebElementToBeVisible ( checkoutButton );
        checkoutButton.click ( );
        return new LoginPage ( driver );
    }

    public ChoseAdressesToCheckOutStep3Page clickOnCheckoutButtonAsAuthenticated ( ) {
        longWaitForWebElementToBeVisible ( checkoutButton );
        checkoutButton.click ( );
        return new ChoseAdressesToCheckOutStep3Page ( driver );
    }

    public void clickOnDeleteButtonWithIndex ( int i ) {
        try {
            longWaitForWebElementToBeVisible ( deleteButton.get ( i ) );
            deleteButton.get ( i ).click ( );
        }
        catch ( IndexOutOfBoundsException e ) {
              Assert.fail ( "Product with index " + i + " not available" );
        }
    }

    public void deleteMoreProductsFromShopingCart ( int numberOfDeletedProducts ) {
        int listSize = deleteButton.size ( ) - 1;
        if ( numberOfDeletedProducts != 0 ) {
            listSize = numberOfDeletedProducts;
        }
        for ( int i = listSize; i >= 1; i-- ) {
            clickOnDeleteButtonWithIndex ( i );

        }
    }

    public void deleteAllProductsFromShopingCart ( ) {
        for ( int i = deleteButton.size ( ) - 1; i >= 0; i-- ) {
            clickOnDeleteButtonWithIndex ( i );
        }

    }

    public String getAlertMessage ( ) {
        String message = "NotDisplayed";
        if ( isWebElementDisplayed ( allertMessage ) ) message = allertMessage.getText ( );

        return message;
    }

    public String getTotalPriceFromCart ( ) {
        waitForWebElementToBeVisible ( totalPriceInCart );
        return totalPriceInCart.getText ( );
    }

}
