package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class ChooseShippingToCheckOutStep4Page extends PageBase {

    @FindBy ( id = "uniform-cgv" )
    private WebElement agreementCheckBox;
    @FindBy ( name = "processCarrier" )
    private WebElement proceedeToCheckOutStep4Button;

    public ChooseShippingToCheckOutStep4Page ( WebDriver driver ) {
        super ( driver );
    }

    public ChoosePaymentMethodToCheckOutStep5 agreeAndProceede ( ) {
        if ( ! ( agreementCheckBox.isSelected ( ) ) ) {
            agreementCheckBox.click ( );
        }
        proceedeToCheckOutStep4Button.click ( );
        return new ChoosePaymentMethodToCheckOutStep5 ( driver );
    }
}
