package appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SimpleTest {

    private AppiumDriver driver;
    /**
     * Run before each test *
     */
    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.2.1");
        capabilities.setCapability("platformVersion", "7.1" );
        capabilities.setCapability("platformName", "ios" );
        capabilities.setCapability("deviceName", "iPhone Simulator" );
        String userDir = System.getProperty("user.dir");
        String localApp = "Startsiden.app";
        String appPath = Paths.get(userDir, "build" , "ios",  localApp).toAbsolutePath().toString();
        capabilities.setCapability("app", appPath);
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Run after each test *
     */
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

    @Rule
    public TestRule printTests = new TestWatcher() {
        protected void starting(Description description) {
            System.out.print("  start test: " + description.getMethodName());
        }

        protected void finished(Description description) {
            System.out.println();
        }
    };

    @org.junit.Test
    public void testFirstScreen() throws Exception {

        WebElement pullToRefresh = this.driver.findElement( By.id( "pulltorefresh" ) );;
        assertTrue( pullToRefresh.isDisplayed() );
    }
}
