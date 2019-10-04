package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;
import resources.TestBase;
import static org.junit.Assert.assertTrue;
import static resources.ProductTypeTabEnum.WOMEN;

public class CanPayInTwoWaysTest extends TestBase {
    private HomePage homePage;
    private String mail = "testioio@mailnesia.com";
    private String password = "12345678";

    public ChoosePaymentMethodToCheckOutStep5 jumpToStep5(){
        int numberOfProducts = 2;

        ResultPage resultPage = homePage.getHeaderElements ().navigateTo(WOMEN);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton();
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = loginPage.loginAsForCheckOut(mail, password);
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        return chooseShippingToCheckOutStep4Page.agreeAndProceede();
    }

    @Before
    public void setUp() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        homePage = new HomePage(driver);
    }

    @Test
    public void canPayByCheckStep1() {
        CheckPaymentPage checkPaymentPage = jumpToStep5 ().clickOnPayByCheckButton();


        assertTrue(checkPaymentPage.getTitleText().contains("CHECK")  );
    }

    @Test
    public void canPayByCheckStep2() {
        CheckPaymentPage checkPaymentPage = jumpToStep5 ().clickOnPayByCheckButton();
        OrderConfirmationPage orderConfirmationPage = checkPaymentPage.clickOnOrderConfirmationFromCheckButton();


        assertTrue(orderConfirmationPage.getConfirmationForPayByCheckMessage().contains("YOUR CHECK MUST INCLUDE") );
    }

    @Test
    public void canPayByBankWireStep1() {
        BankWirePaymentPage bankWirePaymentPage = jumpToStep5 ().clickOnbankWirePaymentButton();


        assertTrue(bankWirePaymentPage.getTitleText().contains("BANK-WIRE")  );
    }
    @Test
    public void canPayByBankWireStep2() {
        BankWirePaymentPage bankWirePaymentPage = jumpToStep5 ().clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


        assertTrue(orderConfirmationPage.getConfirmationForPayByBankWireMessage().contains("complete") );
    }
}