package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DialogFrame {

    private SelenideElement inputFieldCollectionName = $(By.xpath("//div[@role = \"dialog\"]//input"));

    private SelenideElement createCollectionButton = $(By.xpath("//div[@role = \"dialog\"]/div[contains(@class, \"MuiDialogActions-root\")]//button[@aria-label]"));

    private SelenideElement deleteCollectionButton = $(By.xpath("//div[@role = \"dialog\"]/div/button/span[1]"));

    @Step("Ввести название создаваемой коллекции")
    public DialogFrame inputCollectionName(String text){
        inputFieldCollectionName.sendKeys(text);
        return this;
    }

    @Step("Нажать на кнопку создания коллекции")
    public CollectionsPage clickCreateCollectionButton(){
        createCollectionButton.click();
        return page(CollectionsPage.class);
    }

    @Step("Нажать на кнопку удаления коллекции")
    public CollectionsPage clickDeleteCollectionButton(){
       deleteCollectionButton.click();
        return page(CollectionsPage.class);
    }
}
