package ru.geekbrains.lesson;

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

    public CollectionsPage clickCollectionsButton(){
        webDriverWait.until(ExpectedConditions.visibilityOf(collectionsButton));
        collectionsButton.click();
        return new CollectionsPage(driver);
    }
}
