package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import resources.PageBase;

public class CreateAnAccountPage extends PageBase {
    @FindBy ( id = "submitAccount" )
    private WebElement registerButton;
    @FindBy ( id = "submitAddress" )
    private WebElement submitAddressButton;
    @FindBy ( id = "id_gender1" )
    private WebElement mrGenderCustommerOptionRadioButton;
    @FindBy ( id = "id_gender2" )
    private WebElement mrsGenderCustommerOptionRadioButton;
    @FindBy ( id = "customer_firstname" )
    private WebElement firstNameCustommerField;
    @FindBy ( id = "customer_lastname" )
    private WebElement lastNameCustommerField;
    @FindBy ( id = "email" )
    private WebElement emailCustommerField;
    @FindBy ( id = "passwd" )
    private WebElement passwdCustommerField;
    @FindBy ( id = "days" )
    private WebElement dayBirtCustommerDropDown;
    @FindBy ( id = "months" )
    private WebElement monthBirtCustommerDropDown;
    @FindBy ( id = "years" )
    private WebElement yearBirthCustommerDropDown;
    @FindBy ( id = "newsletter" )
    private WebElement newsletterCustommerCheckBox;
    @FindBy ( id = "uniform-optin" )
    private WebElement receivSpecialOferCheckBox;
    @FindBy ( id = "firstname" )
    private WebElement firstNamePersonalField;
    @FindBy ( id = "lastname" )
    private WebElement lastNamePersonalField;
    @FindBy ( id = "company" )
    private WebElement companyField;
    @FindBy ( id = "address1" )
    private WebElement addressStreetField;
    @FindBy ( id = "address2" )
    private WebElement addressAppFloorField;
    @FindBy ( id = "city" )
    private WebElement cityField;
    @FindBy ( id = "id_state" )
    private WebElement stateDropDown;
    @FindBy ( id = "postcode" )
    private WebElement postalCodeField;
    @FindBy ( id = "id_country" )
    private WebElement countryDropDown;
    @FindBy ( id = "other" )
    private WebElement additionalInformationField;
    @FindBy ( id = "phone" )
    private WebElement homePhoneField;
    @FindBy ( id = "phone_mobile" )
    private WebElement mobilePhoneField;
    @FindBy ( id = "alias" )
    private WebElement addressAlliasField;
    @FindBy ( xpath = "//*[@class='alert alert-danger']/ol" )
    private WebElement errorsText;


    public CreateAnAccountPage ( WebDriver driver ) {
        super ( driver );
    }


    public MyAccountPage clickOnRegisterButton ( ) {
        registerButton.click ( );
        return new MyAccountPage ( driver );
    }

    public ChoseAdressesToCheckOutStep3Page clickOnRegisterButtonToOrder ( ) {
        registerButton.click ( );
        return new ChoseAdressesToCheckOutStep3Page ( driver );
    }

    public CreateAnAccountPage clickOnRegisterButtonAndExpectErrors ( ) {
        registerButton.click ( );
        return new CreateAnAccountPage ( driver );
    }


    //sex = "MR"/"MRS"
    public void selectMRorMRSRadioButton ( String sex ) {
        wait.until ( ExpectedConditions.visibilityOf ( mrGenderCustommerOptionRadioButton ) );

        if ( sex == "MR" ) {
            mrGenderCustommerOptionRadioButton.click ( );
        } else if ( sex == "MRS" ) {
            mrsGenderCustommerOptionRadioButton.click ( );
        } else System.out.println ( sex + "is  a non accepted value(only !!MR&MRS!!) " );

    }

    public void selectDateOfBirthDropDown ( String day , String month , String year ) {

        wait.until ( ExpectedConditions.visibilityOf ( dayBirtCustommerDropDown ) );
        dayBirtCustommerDropDown.click ( );
        Select daySelector = new Select ( dayBirtCustommerDropDown );
        daySelector.selectByValue ( day );


        wait.until ( ExpectedConditions.visibilityOf ( monthBirtCustommerDropDown ) );
        monthBirtCustommerDropDown.click ( );
        Select monthSelector = new Select ( monthBirtCustommerDropDown );
        monthSelector.selectByValue ( month );

        wait.until ( ExpectedConditions.visibilityOf ( yearBirthCustommerDropDown ) );
        yearBirthCustommerDropDown.click ( );
        Select yearSelector = new Select ( yearBirthCustommerDropDown );
        yearSelector.selectByValue ( year );
    }

    public void selectStateDropDown ( String state ) {
        Select stateSelector = new Select ( stateDropDown );
        stateSelector.selectByValue ( state );
    }

    public void selectcountryDropDown ( String country ) {
        Select countrySelector = new Select ( countryDropDown );
        countrySelector.selectByValue ( country );
    }


    public void fillPersonalInformation (
            String sex , String firstCustName , String lastCustName , String email , String pass , Boolean newsletter , Boolean offers , String firstName1 , String lastName1 , String day , String month , String year , String state , String company , String address1 , String street , String city , String zip , String country , String info , String phone , String mobile , String allias
    ) {
        selectMRorMRSRadioButton ( sex );
        wait.until ( ExpectedConditions.visibilityOf ( firstNameCustommerField ) );
        firstNameCustommerField.clear ( );
        firstNameCustommerField.sendKeys ( firstCustName );
        lastNameCustommerField.clear ( );
        lastNameCustommerField.sendKeys ( lastCustName );
        emailCustommerField.clear ( );
        emailCustommerField.sendKeys ( email );
        passwdCustommerField.clear ( );
        passwdCustommerField.sendKeys ( pass );
        // selectDateOfBirthDropDown(day,month,year);
        firstNamePersonalField.clear ( );
        firstNamePersonalField.sendKeys ( firstName1 );
        lastNamePersonalField.clear ( );
        lastNamePersonalField.sendKeys ( lastName1 );
//        wait.until(ExpectedConditions.visibilityOf(newsletterCustommerCheckBox));
//        if (newsletter == true) newsletterCustommerCheckBox.click();
//        if (offers == true) receivSpecialOferCheckBox.click();
//        selectDateOfBirthDropDown(day,month,year);
        companyField.sendKeys ( company );
        addressAppFloorField.sendKeys ( address1 );
        addressStreetField.sendKeys ( street );
        cityField.sendKeys ( city );
        selectStateDropDown ( state );
        postalCodeField.sendKeys ( zip );
        selectcountryDropDown ( country );
        additionalInformationField.sendKeys ( info );
        homePhoneField.sendKeys ( phone );
        mobilePhoneField.sendKeys ( mobile );
        addressAlliasField.sendKeys ( allias );
    }

    public void fillAddress ( String street ) {
        addressStreetField.clear ( );
        addressStreetField.sendKeys ( street );
    }

    public ChoseAdressesToCheckOutStep3Page clickOnSaveAdressButton ( ) {
        waitForWebElementToBeVisible ( submitAddressButton );
        submitAddressButton.click ( );
        return new ChoseAdressesToCheckOutStep3Page ( driver );

    }


}
