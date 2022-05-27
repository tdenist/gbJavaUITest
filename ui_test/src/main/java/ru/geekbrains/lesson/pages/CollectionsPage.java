package ru.geekbrains.lesson.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CollectionsPage {

    private final static String collectionsGridLocator = "//div[@data-automation=\"collections-body_folders\"]/div";

    private final static String collectionDropdownMenuButtonLocator = "//button[@data-automation=\"folder-card-menu_trigger\"]";

    private final static String collectionDropdownMenuListLocator = "//ul[@class = \"MuiList-root MuiMenu-list MuiList-padding\"]/li/div";

    private final static String collectionPageSnackbarLocator = "//div[@data-automation = \"Snackbar_Alert\"]";

    private ElementsCollection collectionsInGridList = $$(By.xpath(collectionsGridLocator));

    private SelenideElement collectionDropdownMenuButton = $(By.xpath(collectionDropdownMenuButtonLocator));

    private ElementsCollection collectionDropdownMenu = $$(By.xpath(collectionDropdownMenuListLocator));

    private SelenideElement createButton = $(By.xpath("//div[contains(@class,\"MuiBox-root\")]//header//button"));

    private SelenideElement collectionPageSnackbar = $(By.xpath(collectionPageSnackbarLocator));

    @Step("Нажать на кнопку \"Создать\"")
    public DialogFrame clickCreateButton(){
        createButton.click();
        return page(DialogFrame.class);
    }

    @Step("Нажать на кнопку ... для вызова меню у коллекции")
    public CollectionsPage clickCollectionDropdownMenuButton(){
        collectionsInGridList
                .get(1)
                .findElement(By.xpath(collectionDropdownMenuButtonLocator))
                .click();
        return page(CollectionsPage.class);
    }

    @Step("Выбрать пункт меню")
    public DialogFrame selectCollectionDropdownMenuOption(String menuOption){
        collectionDropdownMenuButton.click();
        /*
        actions
                .moveToElement(collectionDropdownMenuButton)
                .click(collectionDropdownMenuButton)
                .build().perform();
         */
        collectionDropdownMenu.findBy(Condition.text(menuOption)).click();
        /*
        actions
                .moveToElement(collectionDropdownMenu.stream().filter(o -> o.getText().contains(menuOption)).findFirst().get())
                .click(collectionDropdownMenu.stream().filter(o -> o.getText().contains(menuOption)).findFirst().get())
                .build().perform();
         */
        return page(DialogFrame.class);
    }

    @Step("Посчитать количество коллекций")
    public int getCollectionsCount(){
        return collectionsInGridList.size();
    }

    @Step("Проверить отображение всплывающего сообщения")
    public CollectionsPage checkSnackbarIsDisplay(){
        collectionPageSnackbar.shouldBe(Condition.visible);
        return page(CollectionsPage.class);
    }
}
