package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //локаторы
    //private final By enterToAccountButton = By.xpath("/html/body/div/div/main/section[2]/div/button");

    private final By profileHeader = By.xpath("/html/body/div/div/main/div/nav/ul/li[1]/a");





    //методы

    public boolean isProfileHeaderVisible() {
        return driver.findElement(profileHeader).isDisplayed();
    }

}
