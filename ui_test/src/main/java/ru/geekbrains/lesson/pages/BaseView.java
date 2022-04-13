package ru.geekbrains.lesson.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseView {
    WebDriver driver;

    public BaseView(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
