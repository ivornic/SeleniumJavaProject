package pages;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import resources.PageBase;

public class BankWirePaymentPage extends PageBase {

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement orderConfirmationButton;
    @FindBy(className="page-subheading")
    private WebElement titleText;


    public BankWirePaymentPage(WebDriver driver){
        super(driver);
    }
    public String getTitleText(){
        waitForWebElementToBeVisible(titleText);
        return titleText.getText();
    }


    public OrderConfirmationPage clickOnOrderConfirmationFromBankWireButton(){
        orderConfirmationButton.click();
        return new OrderConfirmationPage(driver);
    }
}