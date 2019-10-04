package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import resources.PageBase;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

public class FooterPage extends PageBase {
    @FindBy ( partialLinkText = "https://www.facebook.com" )
    private WebElement       facebookLink;
    @FindBy ( partialLinkText = "https://twitter.com" )
    private WebElement       twitterLink;
    @FindBy ( partialLinkText = "ttps://www.youtube.com" )
    private WebElement       youtubeLink;
    @FindBy ( partialLinkText = "https://plus.google.com/" )
    private WebElement       googlePlusLink;
    @FindBy ( xpath = "//*[@id = 'social_block']/ul/li/a" )
    private List<WebElement> socialLink;

    public FooterPage ( WebDriver driver ) {
        super ( driver );
    }

    public List<String> getSocialLinks ( ) {
        List<String> links = new ArrayList<String> ( );
        for ( WebElement link : socialLink )
            links.add ( link.getAttribute ( "href" ) );
        return links;
    }

    public void clickSocialLinkWithIndex ( int i ) {
        System.out.println ( socialLink );
        try {
            socialLink.get ( i ).click ( );
        }
        catch ( IndexOutOfBoundsException e ) {
            fail ( "Social link with index" + i + "not available" );
        }
    }
}

