package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

public class MyAccountPage extends PageBase {
    @FindBy (xpath ="//span[contains(text(),'My personal information')]")
    private WebElement myPersonalInfo;


    public MyAccountPage ( WebDriver driver ) {super ( driver ); }

    public  PersonalInformation clickOnMyPersonalInfo(){
        myPersonalInfo.click();
        return new PersonalInformation(driver);
    }


}
