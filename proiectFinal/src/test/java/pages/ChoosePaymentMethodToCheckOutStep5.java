package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class ChoosePaymentMethodToCheckOutStep5 extends PageBase {

    @FindBy ( className = "bankwire" )
    private WebElement bankWirePaymentButton;
    @FindBy ( className = "cheque" )
    private WebElement checkPaymentButton;

    public ChoosePaymentMethodToCheckOutStep5 ( WebDriver driver ) {
        super ( driver );
    }

    public BankWirePaymentPage clickOnbankWirePaymentButton ( ) {
        bankWirePaymentButton.click ( );
        return new BankWirePaymentPage ( driver );
    }

    public CheckPaymentPage clickOnPayByCheckButton ( ) {
        checkPaymentButton.click ( );
        return new CheckPaymentPage ( driver );
    }

}
