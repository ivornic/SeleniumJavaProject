package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;


public class ChoseAdressesToCheckOutStep3Page extends PageBase {

    @FindBy ( name = "processAddress" )
    private WebElement proceedeToCheckOutStep3Button;
    @FindBy ( name = "message" )
    private WebElement orderCommentField;
    @FindBy ( xpath = "//*[contains(@class,'submit')]" )
    private WebElement addNewAdressButton;
    @FindBy ( xpath = "//ul[@id='address_delivery']//span" )
    private WebElement updateDeliveryAddressButton;
    @FindBy ( xpath = "//ul[@id='address_delivery']//li[contains(@class, 'address_address1')]" )
    private WebElement deliveryAdressText;


    public ChoseAdressesToCheckOutStep3Page ( WebDriver driver ) {
        super ( driver );
    }

    public ChooseShippingToCheckOutStep4Page clickOnProceedeToCheckOutStep3Button ( ) {
        proceedeToCheckOutStep3Button.click ( );
        return new ChooseShippingToCheckOutStep4Page ( driver );
    }

    public void addComment ( String comment ) {
        longWaitForWebElementToBeVisible ( orderCommentField );
        orderCommentField.clear ( );
        orderCommentField.click ( );
        orderCommentField.sendKeys ( comment );
    }

    public CreateAnAccountPage clickOnDeliveryAdressButton ( ) {
        waitForWebElementToBeVisible ( updateDeliveryAddressButton );
        updateDeliveryAddressButton.click ( );
        return new CreateAnAccountPage ( driver );
    }

    public String getDeliveryAddress ( ) {
        waitForWebElementToBeVisible ( deliveryAdressText );
        return deliveryAdressText.getText ( );
    }
}
