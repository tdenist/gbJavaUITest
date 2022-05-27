package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CatalogPage {

    private SelenideElement collectionsButton = $(By.xpath("//a[@data-tour-stop=\"quick-access-collections\"]"));

    @Step("Нажать кнопку \"Коллекции\"")
    public CollectionsPage clickCollectionsButton(){
        collectionsButton.click();
        return page(CollectionsPage.class);
    }
}
