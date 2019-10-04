package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;


public class HomePage extends PageBase {
    @FindBy ( id = "search_query_top" )
    private WebElement searchField;
    @FindBy ( name = "submit_search" )
    private WebElement submitButton;

    public HomePage ( WebDriver driver ) {
        super ( driver );
    }

    public ResultPage searchFor ( String query ) {
        waitForWebElementToBeVisible ( searchField );
        searchField.clear ( );
        searchField.sendKeys ( query );
        submitButton.click ( );

        return new ResultPage ( driver );
    }

    public HeaderPage getHeaderElements ( ) {
        return new HeaderPage ( driver );
    }
}
