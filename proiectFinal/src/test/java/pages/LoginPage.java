package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.PageBase;

import javax.security.auth.Subject;
import java.util.List;

public class LoginPage extends PageBase {
    @FindBy ( xpath = "//img[contains(@class, 'logo')]" )
    private WebElement        logo;
    @FindBy ( partialLinkText = "Contact us" )
    private WebElement        contact_usLink;
    @FindBy ( linkText = "login" )
    private WebElement        LoginLink;
    @FindBy ( className = "ajax_cart_no_product" )
    private WebElement        cardValue;
    @FindBy ( id = "email" )
    private WebElement        emailadressField;
    @FindBy ( id = "passwd" )
    private WebElement        emailpassField;
    @FindBy ( id = "SubmitLogin" )
    private WebElement        signInButon;
    @FindBy ( className = "alert alert-danger" )
    private WebElement        signinfailMessage;
    @FindBy ( xpath = "//section[@id='block_various_links_footer']//a" )
    private List <WebElement> footerInformationLinks;
    private List <WebElement> failedSignupErrors;
    @FindBy ( id = "email_create" )
    private WebElement        emailCreateField;
    @FindBy ( id = "SubmitCreate" )
    private WebElement        submitCreateButton;


    public LoginPage ( WebDriver driver ) {
        super ( driver );
    }

    public MyAccountPage loginAs ( String email , String password ) {
        waitForWebElementToBeVisible ( emailadressField );
        emailadressField.sendKeys ( email );
        emailpassField.sendKeys ( password );
        signInButon.click ( );
        return new MyAccountPage ( driver );
    }

    public LoginPage loginAndExpectErrors ( String email , String password ) {
        emailadressField.sendKeys ( email );
        emailpassField.sendKeys ( password );
        signInButon.click ( );
        return new LoginPage ( driver );
    }

    public CreateAnAccountPage fillEmailCreateAnAcc ( String mail ) {
        emailCreateField.clear ( );
        emailCreateField.sendKeys ( mail );
        submitCreateButton.click ( );
        return new CreateAnAccountPage ( driver );
    }

    public ChoseAdressesToCheckOutStep3Page loginAsForCheckOut ( String email , String password ) {
        //wait.until(visiblityOf(emailInput));
        emailadressField.sendKeys ( email );
        emailpassField.sendKeys ( password );
        signInButon.click ( );
        return new ChoseAdressesToCheckOutStep3Page ( driver );
    }

    public String getErrorMessage ( ) {
        return signinfailMessage.getText ( );
    }
}