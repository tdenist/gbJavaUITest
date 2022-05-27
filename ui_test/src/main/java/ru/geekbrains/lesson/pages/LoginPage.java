package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private SelenideElement inputFieldUsernameOrEmail = $(By.xpath("//input[@data-test-id=\"email-input\"]"));

    private SelenideElement inputFieldPassword = $(By.xpath("//input[@data-test-id=\"password-input\"]"));

    private SelenideElement submitButton = $(By.xpath("//button[@type=\"submit\"]"));

    @Step("Ввод логина и пароля. Нажатие кнопки логина.")
    public MainPage login(String email, String password){
        inputFieldUsernameOrEmail.sendKeys(email);
        inputFieldPassword.sendKeys(password);
        submitButton.click();
        return page(MainPage.class);
    }
}
