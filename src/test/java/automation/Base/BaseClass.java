package automation.Base;


import automation.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

    public class BaseClass {

        public WebDriver driver;
        @BeforeClass
        public void launchBrowser() {

            String browser = PropertyReader.get("browser");   // from config.properties

            switch (browser.toLowerCase()) {

                case "chrome":
//                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                default:
                    System.out.println("Invalid browser in config.properties");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }


            // Browser settings
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            String url = PropertyReader.get("url");
            driver.get(url);

        }
        @AfterClass
        public void quitBrowser() {
            if (driver != null) {
           driver.quit();
            }
        }
    }



