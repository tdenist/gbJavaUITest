package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    private SelenideElement loginButton = $(By.xpath("//a[@data-automation=\"loginButton\"]"));

    private SelenideElement catalogButton = $(By.xpath("//a[@data-automation=\"utility-bar_catalog\"]"));

    @Step("Клик на иконку логина")
    public LoginPage clickLoginButton(){
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Нажать кнопку \"Каталог\"")
    public CatalogPage clickCatalogButton(){
        catalogButton.click();
        return page(CatalogPage.class);
    }
}
