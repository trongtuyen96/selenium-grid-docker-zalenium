import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DockerStandaloneTest {
    public WebDriver driver;
    @Parameters({"browser"})
    @BeforeTest
    public void setDriver(String browser) throws MalformedURLException {
        switch(browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4445"),chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4446"),firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4447"),edgeOptions);
                break;
        }
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
