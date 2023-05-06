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
                driver = new ChromeDriver();
                break;
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                options = options.setBinary(PATH_YANDEX_BROWSER_EXE);
                driver = new ChromeDriver(options);
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WEB_DRIVER_WAIT_SECONDS_TIMEOUT));


        switch (url) {
            case "main":
                driver.navigate().to(URL);
            case "reg":
                driver.navigate().to(URL_REG);
                break;
            default: throw new RuntimeException("URL " + browserName + " not exist");
        }

        return driver;
    }
}
