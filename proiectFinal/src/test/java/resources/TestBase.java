package resources;

import com.intuit.karate.junit4.Karate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.util.UUID;

public class TestBase {
    protected ChromeDriver driver;

    public static String getRandomEmailAddress( ) {
        return ( UUID.randomUUID ( ).toString ( ).substring ( 0 , 15 ) + "@mailnesia.com" );
    }

    public static String getRandomAddress( ) {
        return ( "Str. " + UUID.randomUUID ( ).toString ( ).substring ( 0 , 15 ) + " N0 2" );
    }

    @Before
    public void baseSetUp( ) {
        System.setProperty ( "webdriver.chrome.driver" , "C:\\Me\\01_Selenium\\02_Workspace\\chromedriver.exe" );
        driver = new ChromeDriver ( );
    /*
       System.setProperty ( "webdriver.gecko.driver" , "D:\\01_Selenium\\02_Workspace\\geckodriver.exe" );
        driver = new FirefoxDriver();
      */
        driver.manage ( ).window ( ).maximize ( );
    }

    @After
    public void tearDown( ) {
        driver.quit ( );
    }

}
