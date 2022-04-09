package ru.geekbrains.lesson.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DialogFrame extends BaseView{

    @FindBy(xpath = "//input[@data-automation=\"CollectionTitleInputDialog_input-active\"]")
    private WebElement inputFieldCollectionName;

    @FindBy(xpath = "//button[@data-automation=\"CollectionTitleInputDialog_submit_button-active\"]")
    private WebElement createCollectionButton;

    @FindBy(xpath = "//button[@data-automation=\"delete-dialog-submit\"]")
    private WebElement deleteCollectionButton;

    public DialogFrame(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести название создаваемой коллекции")
    public DialogFrame inputCollectionName(String text){
        inputFieldCollectionName.sendKeys(text);
        return new DialogFrame(driver);
    }

    @Step("Нажать на кнопку создания коллекции")
    public CollectionsPage clickCreateCollectionButton(){
        createCollectionButton.click();
        return new CollectionsPage(driver);
    }

    @Step("Нажать на кнопку удаления коллекции")
    public CollectionsPage clickDeleteCollectionButton(){
       deleteCollectionButton.click();
        return new CollectionsPage(driver);
    }
}
