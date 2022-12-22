import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGridTest {
    public WebDriver driver;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    @Parameters({"browser"})
    @BeforeTest
    public void setDriver(String browser) throws MalformedURLException {
        switch(browser){
            case "chrome":
                capabilities.setBrowserName("chrome");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(capabilities);
                break;
            case "edge":
                // Track on http://localhost:4444/status to check the name of browser name that currently supported
                capabilities.setBrowserName("MicrosoftEdge");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(capabilities);
                break;
            case "firefox":
                capabilities.setBrowserName("firefox");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.merge(capabilities);
                break;
        }
        driver = new RemoteWebDriver(new URL("http://localhost:4444"),capabilities);
        driver.get("https://www.automatedtestingwithtuyen.com");
    }

    @Test(priority = 0)
    public void goToFrameworksTest() throws InterruptedException {
        driver.get("https://www.automatedtestingwithtuyen.com/frameworks");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.automatedtestingwithtuyen.com/frameworks"), "User is in Frameworks page");
    }

    @Test(priority = 1)
    public void goToToolsTest() throws InterruptedException {
        driver.get("https://www.automatedtestingwithtuyen.com/tools");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.automatedtestingwithtuyen.com/tools"), "User is in Tools page");
    }

    @Test(priority = 2)
    public void goToAuthorTest() throws InterruptedException {
        driver.get("https://www.automatedtestingwithtuyen.com/about");
        Thread.sleep(2000);
        Assert.assertTrue(driver.getCurrentUrl().equals("https://www.automatedtestingwithtuyen.com/about"), "User is in Author page");
    }

    @AfterTest
    public void close() {
        driver.close();
    }
}
