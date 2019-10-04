package resources;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class PageBase {
    protected WebDriver     driver;
    protected WebDriverWait wait, longWait;

    public PageBase ( WebDriver driver ) {
        this.driver = driver;
        PageFactory.initElements ( driver , this );
        wait = new WebDriverWait ( driver , 10 );
        longWait = new WebDriverWait ( driver , 20 );
    }

    public void waitForWebElementToBeVisible ( WebElement element ) {
        wait.until ( visibilityOf ( element ) );
    }

    public void waitForWebElementsToBeVisible ( List <WebElement> elements ) {
        wait.until ( visibilityOfAllElements ( elements ) );
    }

    public void longWaitForWebElementToBeVisible ( WebElement element ) {
        longWait.until ( visibilityOf ( element ) );
    }

    public void waitForPageToBeVisible ( WebDriver driver , Integer time , Integer numberOfWindows ) {

        new WebDriverWait ( driver , time ).until ( ExpectedConditions.numberOfWindowsToBe ( numberOfWindows ) );
    }

    public boolean isWebElementDisplayed ( WebElement element ) {
        try {
            longWaitForWebElementToBeVisible ( element );
            return true;
        }
        catch ( TimeoutException e ) {
            return false;
        }

    }
}
