package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;
import resources.TestBase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class CanOrderAsNonAuthenticatedUserTest extends TestBase {

    private HomePage homePage;

    public String addProductsAndJumpToConfirmation ( int i ){

        String query = "summer";
        String mail= getRandomEmailAddress();

        ResultPage resultPage= homePage.searchFor(query);
        ProductPage productPage = resultPage.clickOnResultWithIndex(i);
        ShoppingCartPage shoppingCartPage = productPage.submitAndCheckout();
        LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton();
        CreateAnAccountPage createAnAccountPage = loginPage.fillEmailCreateAnAcc(mail);
        createAnAccountPage.fillPersonalInformation("MR", "ale", "nad", mail, "qwerty", true, true, "Cristina", "Vornic", "11", "11", "2000",
                                                    "1", "OCE", "ap1", "Mihai Viteazul", "Timisoara", "00010", "21", "Yosdfos", "541345153", "541345153", "sdgsdgsd");
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();
        return orderConfirmationPage.getConfirmationForPayByBankWireMessage();
    }

    public String addAndRemoveProductsThanJumpToConfirmation(int numberOfAddedProducts,int numberOfDeletedProducts){
        String query = "summer";
        String mail = getRandomEmailAddress();

        ResultPage resultPage = homePage.searchFor(query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfAddedProducts);
        shoppingCartPage.deleteMoreProductsFromShopingCart(numberOfDeletedProducts);
        LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton();
        CreateAnAccountPage createAnAccountPage = loginPage.fillEmailCreateAnAcc(mail);
        createAnAccountPage.fillPersonalInformation("MR", "ale", "nad", mail, "qwerty", true, true, "Cristina", "Vornic", "11", "11", "2000",
                                                    "1", "OCE", "ap1", "Mihai Viteazul", "Timisoara", "00010", "21", "Yosdfos", "541345153", "541345153", "sdgsdgsd");
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();
        return orderConfirmationPage.getConfirmationForPayByBankWireMessage();
    }


    @Before
    public void setUp(){
        driver.get("http://automationpractice.com/index.php");
        homePage = new HomePage (driver);
    }

    @Test
    public void canCheckoutWithOneProduct(){


         assertThat ( addProductsAndJumpToConfirmation ( 1 ), containsString ( "complete" ) ) ;
    }

    @Test
    public void canCheckoutWithMoreProducts() {
        int numberOfProducts = 0;// 0 = all found products


        assertThat ( addProductsAndJumpToConfirmation ( numberOfProducts ), containsString ( "complete" ) );
    }


    @Test
    public void canCheckOutRemovingOneProducts(){
        int numberOfAddedProducts = 0;// 0 = all found products


        assertThat( addAndRemoveProductsThanJumpToConfirmation (numberOfAddedProducts,1  ), containsString("complete"));
    }

    @Test
    public void canCheckOutRemovingMoreProducts(){
        int numberOfProducts = 0;// 0 = all found products
        int numberOfDeletedProducts = 3;// 0 = all found products less first added product


        assertThat(addAndRemoveProductsThanJumpToConfirmation ( numberOfProducts,numberOfDeletedProducts ), containsString("complete"));
    }

    @Test
    public void canNotCheckOutWithoutProductsInShopingCart(){
        String query = "summer";
        int numberOfProducts = 0;// 0 = all found products

        ResultPage resultPage = homePage.searchFor(query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        shoppingCartPage.deleteAllProductsFromShopingCart();
        String message = shoppingCartPage.getAlertMessage();


        assertThat(message, containsString("empty"));
    }

}
