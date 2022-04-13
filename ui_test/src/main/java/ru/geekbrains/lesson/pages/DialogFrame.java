package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class DialogFrame {

    private SelenideElement inputFieldCollectionName = $(By.xpath("//input[@data-automation=\"CollectionTitleInputDialog_input-active\"]"));

    private SelenideElement createCollectionButton = $(By.xpath("//button[@data-automation=\"CollectionTitleInputDialog_submit_button-active\"]"));

    private SelenideElement deleteCollectionButton = $(By.xpath("//button[@data-automation=\"delete-dialog-submit\"]"));

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
