package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;
import resources.ProductTypeTabEnum;

public class HeaderPage extends PageBase {
    public HeaderPage ( WebDriver driver ) {
        super ( driver );
    }

    @FindBy ( id = "search_query_top" )
    private WebElement searchField;
    @FindBy ( name = "submit_search" )
    private WebElement submitButton;
    @FindBy ( xpath = "//a[@class='sf-with-ul'][contains(text(),'Women')]" )
    private WebElement womenTab;
    @FindBy ( xpath = "//ul[contains(@class,'menu-content')]/li/a[@class='sf-with-ul'][contains(text(),'Dresses')]" )
    private WebElement dressesTab;
    @FindBy ( xpath = "//a[@class='sf-with-ul'][contains(text(),'T-shirts')]" )//NOT OK
    private WebElement tshirtTab;

    public ResultPage searchFor ( String query ) {
        waitForWebElementToBeVisible ( searchField );
        searchField.clear ( );
        searchField.sendKeys ( query );
        submitButton.click ( );

        return new ResultPage ( driver );
    }

    public ResultPage navigateTo ( Enum productTypeTab ) {
        if ( ProductTypeTabEnum.WOMEN.equals ( productTypeTab ) ) {
            womenTab.click ( );

        }
        else if ( ProductTypeTabEnum.DRESSES.equals ( productTypeTab ) ) {
            dressesTab.click ( );

        }
        else if ( ProductTypeTabEnum.TSHIRT.equals ( productTypeTab ) ) {
            tshirtTab.click ( );

        }
        else {
            Assert.fail ( "selected tab is not available! " );
        }

        return new ResultPage ( driver );
    }


    public ResultPage loginAndSearchFor ( String mail , String password , String query ) {
        LoginPage loginPage = new LoginPage ( driver );
        loginPage.loginAs ( mail , password );
        HeaderPage headerPage = new HeaderPage ( driver );
        return headerPage.searchFor ( query );
    }

}
