package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import resources.PageBase;

public class PersonalInformation extends PageBase {
@FindBy (id = "firstname")
    private WebElement firstNameEditField;
@FindBy (id="old_passwd")
public WebElement currentPassEditField;
@FindBy (name = "submitIdentity")
private  WebElement saveChangesButton;
@FindBy (xpath = "//p[@class='alert alert-success']")
private  WebElement successMessage;


public  PersonalInformation( WebDriver driver ) {
    super ( driver );
}

public boolean changeFirstName(String newName, String currentPass){
  firstNameEditField.sendKeys(newName);
  currentPassEditField.sendKeys(currentPass);
  saveChangesButton.click();

/*  try {
        longWaitForWebElementToBeVisible ( successMessage );
    }
    catch ( IndexOutOfBoundsException e ) {
        Assert.fail("No changes is saved! ");
    }
    */
  longWaitForWebElementToBeVisible ( successMessage );
  return successMessage.isDisplayed();

}

}
