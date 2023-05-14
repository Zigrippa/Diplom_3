package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static config.Config.*;


public class WebDriverFactory {
    public static WebDriver get(String browserName, String url) {

        WebDriver driver;
        switch (browserName) {
            case "chrome":
                /*ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");*/
                driver = new ChromeDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options = options.setBinary(PATH_YANDEX_BROWSER_EXE);
                driver = new ChromeDriver(options);
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WEB_DRIVER_WAIT_SECONDS_TIMEOUT));

        switch (url) {
            case "main":
                driver.navigate().to(URL);
                break;
            case "reg":
                driver.navigate().to(URL_REG);
                break;
            case "login":
                driver.navigate().to(URL_LOGIN);
                break;
            default: throw new RuntimeException("URL " + url + " not exist");
        }

        return driver;
    }
}
