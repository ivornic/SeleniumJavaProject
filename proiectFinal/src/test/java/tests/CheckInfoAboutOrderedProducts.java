package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;
import resources.TestBase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static resources.ProductTypeTabEnum.WOMEN;

public class CheckInfoAboutOrderedProducts extends TestBase {

    private HomePage homePage;

    @Before
    public void setUp() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        homePage = new HomePage ( driver);

    }

    @Test
    public void checkTotalPriceForOrderedProducts() {
        ResultPage resultPage = homePage.getHeaderElements ().navigateTo(WOMEN);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(1);
        String totalInCart= shoppingCartPage.getTotalPriceFromCart();
        LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton();
        String mail = getRandomEmailAddress();
        CreateAnAccountPage createAnAccountPage =loginPage.fillEmailCreateAnAcc(mail);
        createAnAccountPage.fillPersonalInformation("MR", "ale", "nad", mail, "qwerty", true, true, "Cristina", "Vornic", "11", "11", "2000",
                "8", "OCE", "ap1", "Mihai Viteazul", "Timisoara", "00010", "21", "Yosdfos", "541345153", "541345153", "sdgsdgsd");

        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();
        OrdersPage ordersPage= orderConfirmationPage.clickOnbackToOrdersButton();
        String lastOrderTotal= ordersPage.getTotalPriceFromLastOrder();


        assertThat(totalInCart ,containsString(lastOrderTotal) );
    }
}
