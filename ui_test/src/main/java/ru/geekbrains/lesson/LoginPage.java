package ru.geekbrains.lesson;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseView {

    @FindBy(xpath = "//input[@data-test-id=\"email-input\"]")
    private WebElement inputFieldUsernameOrEmail;

    @FindBy(xpath = "//input[@data-test-id=\"password-input\"]")
    private WebElement inputFieldPassword;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(String email, String password){
        webDriverWait.until(ExpectedConditions.visibilityOf(inputFieldUsernameOrEmail));
        inputFieldUsernameOrEmail.sendKeys(email);
        inputFieldPassword.sendKeys(password);
        submitButton.click();
        return new MainPage(driver);
    }
}
