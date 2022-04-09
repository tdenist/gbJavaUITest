package ru.geekbrains.lesson.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CatalogPage extends BaseView {

    @FindBy(xpath = "//a[@data-tour-stop=\"quick-access-collections\"]")
    private WebElement collectionsButton;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку \"Коллекции\"")
    public CollectionsPage clickCollectionsButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(collectionsButton));
        collectionsButton.click();
        return new CollectionsPage(driver);
    }
}
