package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

import javax.swing.*;
import java.util.List;

public class OrdersPage extends PageBase {


    @FindBy(xpath = "//tr[contains(@class,'first_item')]//td[contains(@class,'history_detail')]//span")
    private WebElement detailsFirstOrder;
    @FindBy(xpath = "//div[@id='block-order-detail']//div[5]//table[1]//tbody[1]//tr[1]//td[2]")
    private WebElement firstCommentFromSelectedOrder;
    @FindBy (xpath="(//h3[text()='Messages']/following-sibling::div//td[2])")
    private List<WebElement> allSelectedOrderComments;
    @FindBy (xpath="(//td[@class='history_price']//span[@class='price'])")
    private List<WebElement> totalPrice;
    @FindBy (xpath="(//h3[text()='Messages']/following-sibling::div//td[2])")
    private WebElement selectedOrderComments;
@FindBy (xpath = "//*[contains(@class,'alternate_item')]//*[@class='address_address1']")
private WebElement deliveryAddress;
    @FindBy(xpath = "//a[contains(@class,'pull-right')]//span")
    private WebElement reorderButton;

    public OrdersPage(WebDriver driver){super(driver);}
    public void clickOnDetailsFirstOrder(){
        waitForWebElementToBeVisible ( detailsFirstOrder );
        detailsFirstOrder.click();
            }

    public String getLastComment(){
       longWaitForWebElementToBeVisible(selectedOrderComments);
        return selectedOrderComments.getText();
    }

    public String getComment(int i){
        String comment="No comment";
        try{
            longWaitForWebElementToBeVisible(allSelectedOrderComments.get(i));
            comment= allSelectedOrderComments.get(i).getText();
        }catch (IndexOutOfBoundsException e){
            Assert.fail("Product with index " + i + " not available");
        }
        return comment;

    }

    public String  getTotalPrice(int i){
        String  price="0";
        try{
            longWaitForWebElementToBeVisible(totalPrice.get(i));
            price = totalPrice.get(i).getText();
        }catch (IndexOutOfBoundsException e){
            Assert.fail("Product with index " + i + " not available");
        }
        return price;

    }
    public String getTotalPriceFromLastOrder(){
          return getTotalPrice(0);
    }

    public String getDeliveryAdress(){
        Actions action = new Actions ( driver );
        action.moveToElement ( reorderButton );
       waitForWebElementToBeVisible ( deliveryAddress );
        return deliveryAddress.getText ();
    }



}


