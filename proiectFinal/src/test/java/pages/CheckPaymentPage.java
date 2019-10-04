package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class CheckPaymentPage extends PageBase {

    @FindBy ( xpath = "//*[@class='page-subheading']" )
    private WebElement titleText;
    @FindBy ( xpath = "//*[@id='cart_navigation']/button" )
    private WebElement orderConfirmationButton;

    public CheckPaymentPage ( WebDriver driver ) {
        super ( driver );
    }

    public String getTitleText ( ) {
        waitForWebElementToBeVisible ( titleText );
        return titleText.getText ( );
    }

    public OrderConfirmationPage clickOnOrderConfirmationFromCheckButton ( ) {
        orderConfirmationButton.click ( );
        return new OrderConfirmationPage ( driver );
    }
}
