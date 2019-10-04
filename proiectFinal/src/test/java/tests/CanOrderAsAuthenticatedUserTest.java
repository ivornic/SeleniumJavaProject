package tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.*;
import resources.TestBase;

import static org.hamcrest.Matchers.containsString;

    @RunWith(JUnitParamsRunner.class)
    public class CanOrderAsAuthenticatedUserTest extends TestBase {

        private HomePage homePage;
        private String query = "summer";
        private String mail= "testioio@mailnesia.com";
        private String password= "12345678";

    @Before
    public void setUp(){
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        homePage = new HomePage ( driver );
            }

    @Test
    public void canCheckoutWithOneProduct(){
        ResultPage resultPage = homePage.getHeaderElements ().loginAndSearchFor(mail,password,query);
        ProductPage productPage = resultPage.clickOnResultWithIndex(0);
        ShoppingCartPage shoppingCartPage = productPage.submitAndCheckout();
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = shoppingCartPage.clickOnCheckoutButtonAsAuthenticated();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


        Assert.assertThat( orderConfirmationPage.getConfirmationForPayByBankWireMessage(),containsString("complete")) ;
    }

    @Test
    @Parameters({"0,SUMMER,testioio@mailnesia.com,12345678",
            "5,DRESSES,testioio@mailnesia.com,12345678",
            "2,PRINTED,testioio@mailnesia.com,12345678"}  )
    public void canCheckoutWithMoreProducts(int numberOfProducts,String query,String mail, String password) {
        ResultPage resultPage = homePage.getHeaderElements ().loginAndSearchFor(mail,password,query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = shoppingCartPage.clickOnCheckoutButtonAsAuthenticated();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


        Assert.assertThat(orderConfirmationPage.getConfirmationForPayByBankWireMessage(), containsString("complete"));
    }

    @Test
    public void canCheckOutRemovingOneProducts(){
        int numberOfProducts = 0;// 0 = all found products

        ResultPage resultPage = homePage.getHeaderElements ().loginAndSearchFor(mail,password,query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        shoppingCartPage.clickOnDeleteButtonWithIndex(0);
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = shoppingCartPage.clickOnCheckoutButtonAsAuthenticated();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


        Assert.assertThat(orderConfirmationPage.getConfirmationForPayByBankWireMessage(), containsString("complete"));
    }

    @Test
    public void canCheckOutRemovingMoreProducts(){
        int numberOfProducts = 0;// 0 = all found products
        int numberOfDeletedProducts = 2;// 0 = all found products less first added product

        HomePage homePage =new HomePage ( driver );
        ResultPage resultPage = homePage.getHeaderElements ().loginAndSearchFor(mail,password,query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        shoppingCartPage.deleteMoreProductsFromShopingCart(numberOfDeletedProducts);
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = shoppingCartPage.clickOnCheckoutButtonAsAuthenticated();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


        Assert.assertThat(orderConfirmationPage.getConfirmationForPayByBankWireMessage(), containsString("complete"));
    }

    @Test
    public void canNotCheckOutWithoutProductsInShopingCart(){
        int numberOfProducts = 0;// 0 = all found products

        ResultPage resultPage = homePage.getHeaderElements ().loginAndSearchFor(mail,password,query);
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
        shoppingCartPage.deleteAllProductsFromShopingCart();
        String message = shoppingCartPage.getAlertMessage();


        Assert.assertThat(message, containsString("Your shopping cart is empty."));
    }
}
