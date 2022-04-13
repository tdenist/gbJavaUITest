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

    private final static String collectionsGridLocator = "//div[@data-automation=\"StandardGrid_content\"]/div";

    private final static String collectionDropdownMenuButtonLocator = "//button[@data-automation=\"ActionsDropdownMenu_icon_actions\"]";

    private final static String collectionDropdownMenuListLocator = "//div[@data-automation=\"StandardGrid_content\"]//div[@data-react-toolbox=\"menu\"]/ul/li/span";

    private final static String collectionPageSnackbarLocator = "//div[@data-automation = \"CollectionPage_snackbar\"]";

    private ElementsCollection collectionsInGridList = $$(By.xpath(collectionsGridLocator));

    private SelenideElement collectionDropdownMenuButton = $(By.xpath(collectionDropdownMenuButtonLocator));

    private ElementsCollection collectionDropdownMenu = $$(By.xpath(collectionDropdownMenuListLocator));

    private SelenideElement createButton = $(By.xpath("//button[@data-track-label=\"createCollectionIcon\"]"));

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
        collectionDropdownMenuButton.hover();
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
