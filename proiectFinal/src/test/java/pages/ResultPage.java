package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

import java.util.List;

public class ResultPage extends PageBase {

    @FindBy (xpath = "//*[@class='product-name' and @itemprop='url']")
    private List <WebElement> results;

    public ResultPage ( WebDriver driver ) {
        super ( driver );
    }

    public ProductPage clickOnResultWithIndex ( int i ) {
        try {
            longWaitForWebElementToBeVisible ( results.get ( i ) );
            results.get ( i ).click ( );
        }
        catch ( IndexOutOfBoundsException e ) {
            Assert.fail ( "Product with index " + i + " not available" );
        }
        return new ProductPage ( driver );

    }

    public ShoppingCartPage addMoreProductsInShopingCart ( int numberOfProducts ) {
        ProductPage productPage = new ProductPage ( driver );
        int listSize = results.size ( ) - 1;
        if ( numberOfProducts != 0 ) {
            listSize = numberOfProducts - 1;
        }

        for ( int i = 0; i <= listSize; i++ ) {
            productPage = clickOnResultWithIndex ( i );
            productPage.addProductToCart ( );
            if ( i < listSize ) {
                productPage.clickOnContinueButton ( );
                driver.navigate ( ).back ( );
            }
        }
        productPage.clickOnProceeedToCheckOut ( );
        return new ShoppingCartPage ( driver );
    }


}