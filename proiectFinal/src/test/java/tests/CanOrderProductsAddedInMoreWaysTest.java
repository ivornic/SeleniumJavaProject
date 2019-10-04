package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;
import resources.TestBase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static resources.ProductTypeTabEnum.WOMEN;

public class CanOrderProductsAddedInMoreWaysTest extends TestBase {
          private HomePage homePage;
          private String mail = "testioio@mailnesia.com";
          private String password = "12345678";

          @Before
          public void setUp() {
              driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
              homePage = new HomePage ( driver);

          }

          @Test
          public void canCheckoutWithOneProduct() {
              int numberOfProducts=3;

              ResultPage resultPage = homePage.getHeaderElements ().navigateTo(WOMEN);
              ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart(numberOfProducts);
              LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton();
              ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = loginPage.loginAsForCheckOut(mail,password);
              ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button();
              ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede();
              BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton();
              OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton();


              assertThat(orderConfirmationPage.getConfirmationForPayByBankWireMessage(), containsString("complete"));
          }
      }
