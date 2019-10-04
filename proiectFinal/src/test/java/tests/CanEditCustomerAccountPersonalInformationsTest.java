package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.PersonalInformation;
import resources.TestBase;

public class CanEditCustomerAccountPersonalInformationsTest extends TestBase {
    private String mail = "testioio@mailnesia.com";
    private String password = "12345678";
    private LoginPage loginPage;


@Before
public void setUp() {
    driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    loginPage  = new LoginPage(driver);
}

@Test
    public  void  canEditFirstNameOfAccount(){
    MyAccountPage myAccountPage = loginPage.loginAs(mail,password);
    PersonalInformation personalInformation = myAccountPage.clickOnMyPersonalInfo();

    Assert.assertTrue(personalInformation.changeFirstName("Ionut", password));

}




}
