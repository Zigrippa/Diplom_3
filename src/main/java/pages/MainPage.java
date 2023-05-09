package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static config.Config.WEB_DRIVER_WAIT_SECONDS_TIMEOUT;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //локаторы
    private final By enterToAccountButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By personalAreaButton = By.xpath("/html/body/div/div/header/nav/a");
    private final By checkoutOrderButton = By.xpath("/html/body/div/div/main/section[2]/div/button");
    private final By stellarisBurgerLogo = By.xpath("/html/body/div/div/header/nav/div/a/svg");


    private final By bunsSelectionButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    private final By sousesSelectionButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    private final By fillingsSelectionButton = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");

    private final By bunsSelection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[1]");
    private final By sousesSelection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[2]");
    private final By fillingsSelection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/h2[3]");


    //методы
    public LoginPage clickToAccountButton() {
        driver.findElement(enterToAccountButton).click();
        return new LoginPage(driver);
    }

    public AccountPage clickPersonalAreaButtonWhileAlreadyLogin() {
        driver.findElement(personalAreaButton).click();
        return new AccountPage(driver);
    }

    public LoginPage clickPersonalAreaButtonWhileIsNotLogin() {
        driver.findElement(personalAreaButton).click();
        return new LoginPage(driver);
    }

    public MainPage clickStellarisBurgerLogo() {
        driver.findElement(stellarisBurgerLogo).click();
        return new MainPage(driver);
    }

    public MainPage clickBunsSelectionButton() {
        driver.findElement(bunsSelectionButton).click();
        return new MainPage(driver);
    }

    public MainPage clickSousesSelectionButton() {
        driver.findElement(sousesSelectionButton).click();
        return new MainPage(driver);
    }

    public MainPage clickFillingsSelectionButton() {
        driver.findElement(fillingsSelectionButton).click();
        return new MainPage(driver);
    }

    public boolean isBunsVisible() {
        return driver.findElement(bunsSelection).isDisplayed() && driver.findElement(bunsSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }

    public boolean isSousesVisible() {
        return driver.findElement(sousesSelection).isDisplayed() && driver.findElement(sousesSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }

    public boolean isFillingsVisible() {
        return driver.findElement(fillingsSelection).isDisplayed() && driver.findElement(fillingsSelectionButton)
                .getAttribute("class")
                .contains("tab_tab_type_current__2BEPc");
    }


    public boolean isCheckoutOrderButtonVisible() {
        return driver.findElement(checkoutOrderButton).isDisplayed();
    }

}
