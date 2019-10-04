package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class OrderConfirmationPage extends PageBase {
    @FindBy ( className = "cheque-indent" )
    private WebElement confirmationForPayByBankWireTitle;
    /*
        Please send us a bank wire with
        - Amount $19.25
        - Name of account owner Pradeep Macharla
        - Include these details xyz
        - Bank name RTP
        - Do not forget to insert your order reference ISEPEDJAD in the subject of your bank wire.
        An email has been sent with this information.
        Your order will be sent as soon as we receive payment.
        If you have questions, comments or concerns, please contact our expert customer support team. .
     */

    @FindBy ( className = "page-subheading" )
    private WebElement confirmationForPayByCheckTitle;
    /*
        You have chosen to pay by check. Here is a short summary of your order:
        - The total amount of your order comes to: $30.16 (tax incl.)
        - We allow the following currencies to be sent by check: Dollar
        - Check owner and address information will be displayed on the next page.
        - Please confirm your order by clicking 'I confirm my order'.
     */

    @FindBy ( xpath = "//a[contains(@class,'button-exclusive')]" )
    private WebElement backToOrdersButton;


    public OrderConfirmationPage ( WebDriver driver ) {
        super ( driver );
    }

    public String getConfirmationForPayByBankWireMessage ( ) {
        waitForWebElementToBeVisible ( confirmationForPayByBankWireTitle );
        return confirmationForPayByBankWireTitle.getText ( );
    }

    public String getConfirmationForPayByCheckMessage ( ) {
        waitForWebElementToBeVisible ( confirmationForPayByCheckTitle );
        return confirmationForPayByCheckTitle.getText ( );
    }

    public OrdersPage clickOnbackToOrdersButton ( ) {
        waitForWebElementToBeVisible ( backToOrdersButton );
        backToOrdersButton.click ( );
        return new OrdersPage ( driver );
    }
}
