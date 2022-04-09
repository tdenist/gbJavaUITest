package ru.geekbrains.lesson.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BaseView {

    @FindBy(xpath = "//a[@data-automation=\"loginButton\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@data-automation=\"utility-bar_catalog\"]")
    private WebElement catalogButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на иконку логина")
    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage(driver);
    }

    @Step("Нажать кнопку \"Каталог\"")
    public CatalogPage clickCatalogButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(catalogButton));
        catalogButton.click();
        return new CatalogPage(driver);
    }
}
