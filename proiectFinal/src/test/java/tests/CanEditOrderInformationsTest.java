package tests;

import org.junit.Before;
import org.junit.Test;
import pages.*;
import resources.TestBase;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static resources.ProductTypeTabEnum.WOMEN;

public class CanEditOrderInformationsTest extends TestBase {
    private String    query    = "summer";
    private String    mail     = "testioio@mailnesia.com";
    private String    password = "12345678";
    private int numberOfProducts = 1;
    private HomePage homePage;

    public CreateAnAccountPage jumpToCreateAnAcc(String mail){
        ResultPage resultPage = homePage.getHeaderElements ().navigateTo ( WOMEN );
        ShoppingCartPage shoppingCartPage = resultPage.addMoreProductsInShopingCart ( numberOfProducts );
        LoginPage loginPage = shoppingCartPage.clickOnCheckoutButton ( );
        return loginPage.fillEmailCreateAnAcc ( mail );
    }

    @Before
    public void setUp ( ) {
        driver.get ( "http://automationpractice.com/index.php?controller=authentication&back=my-account" );
        homePage = new HomePage ( driver );
            }

    @Test
    public void canEditAdressStep1 ( ) {
        String mail = getRandomEmailAddress ( );
        String address = getRandomAddress ( );
        CreateAnAccountPage createAnAccountPage = jumpToCreateAnAcc (mail);
        createAnAccountPage.fillPersonalInformation ( "MR" , "ale" , "nad" , mail , "qwerty" , true , true , "Cristina" , "Vornic" , "11" , "11" , "2000" ,
                                                      "1" , "OCE" , "ap1" , address , "Timisoara" , "00010" , "21" , "Yosdfos" , "541345153" , "541345153" , "sdgsdgsd" );
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder ( );
        createAnAccountPage = choseAdressesToCheckOutStep3Page.clickOnDeliveryAdressButton ( );
        String newAddress = getRandomAddress ( );
        createAnAccountPage.fillAddress ( newAddress );
        choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnSaveAdressButton ( );
        String updatedDeliveryAddress = choseAdressesToCheckOutStep3Page.getDeliveryAddress ( );


        assertThat ( updatedDeliveryAddress , containsString ( newAddress ) );
    }

    @Test
    public void canEditAdressStep2() {
        String mail = getRandomEmailAddress ( );
        String address = getRandomAddress ( );
        CreateAnAccountPage createAnAccountPage = jumpToCreateAnAcc ( mail );
        String address1 = "Ap XX";
        createAnAccountPage.fillPersonalInformation ( "MR", "ale", "nad", mail, "qwerty", true, true, "Cristina", "Vornic", "11", "11", "2000",
                                                      "1", "OCE", address1, address, "Timisoara", "00010", "21", "Yosdfos", "541345153", "541345153", "sdgsdgsd" );
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder();
        String newAddress = getRandomAddress();
        createAnAccountPage = choseAdressesToCheckOutStep3Page.clickOnDeliveryAdressButton();
        createAnAccountPage.fillAddress(newAddress);
        choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnSaveAdressButton();
        String updatedDeliveryAddress = choseAdressesToCheckOutStep3Page.getDeliveryAddress();
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button ( );
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede ( );
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton ( );
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton ( );
        OrdersPage ordersPage = orderConfirmationPage.clickOnbackToOrdersButton ( );
        ordersPage.clickOnDetailsFirstOrder ( );


        assertThat(ordersPage.getDeliveryAdress () +" "+address1, containsString(updatedDeliveryAddress));
        }


    @Test
    public void canEditComments ( ) {
        String mail = getRandomEmailAddress ( );
        String address1 = getRandomAddress ( );
        CreateAnAccountPage createAnAccountPage = jumpToCreateAnAcc ( mail );
        createAnAccountPage.fillPersonalInformation ( "MR" , "ale" , "nad" , mail , "qwerty" , true , true , "Cristina" , "Vornic" , "11" , "11" , "2000" ,
                                                      "1" , "OCE" , address1 , "Mihai Viteazul" , "Timisoara" , "00010" , "21" , "Yosdfos" , "541345153" , "541345153" , "sdgsdgsd" );
        ChoseAdressesToCheckOutStep3Page choseAdressesToCheckOutStep3Page = createAnAccountPage.clickOnRegisterButtonToOrder ( );
        String comment = "CallMe";
        choseAdressesToCheckOutStep3Page.addComment ( comment );
        ChooseShippingToCheckOutStep4Page chooseShippingToCheckOutStep4Page = choseAdressesToCheckOutStep3Page.clickOnProceedeToCheckOutStep3Button ( );
        ChoosePaymentMethodToCheckOutStep5 choosePaymentMethodToCheckOutStep5 = chooseShippingToCheckOutStep4Page.agreeAndProceede ( );
        BankWirePaymentPage bankWirePaymentPage = choosePaymentMethodToCheckOutStep5.clickOnbankWirePaymentButton ( );
        OrderConfirmationPage orderConfirmationPage = bankWirePaymentPage.clickOnOrderConfirmationFromBankWireButton ( );
        OrdersPage ordersPage = orderConfirmationPage.clickOnbackToOrdersButton ( );
        ordersPage.clickOnDetailsFirstOrder ( );


        assertThat ( ordersPage.getLastComment ( ) , containsString ( comment ) );
    }
}