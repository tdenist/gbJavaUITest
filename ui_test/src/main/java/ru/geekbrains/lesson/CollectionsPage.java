package ru.geekbrains.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class CollectionsPage extends BaseView {

    private final static String collectionsGridLocator = "//div[@data-automation=\"StandardGrid_content\"]/div";

    private final static String collectionDropdownMenuButtonLocator = "//button[@data-automation=\"ActionsDropdownMenu_icon_actions\"]";

    private final static String collectionDropdownMenuListLocator = "//div[@data-automation=\"StandardGrid_content\"]//div[@data-react-toolbox=\"menu\"]/ul/li/span";

    private final static String collectionPageSnackbarLocator = "//div[@data-automation = \"CollectionPage_snackbar\"]";

    @FindBy(xpath = collectionsGridLocator)
    private List<WebElement> collectionsInGridList;

    @FindBy(xpath = collectionDropdownMenuButtonLocator)
    private WebElement collectionDropdownMenuButton;

    @FindBy(xpath = collectionDropdownMenuListLocator)
    private List<WebElement> collectionDropdownMenu;

    @FindBy(xpath = "//button[@data-track-label=\"createCollectionIcon\"]")
    private WebElement createButton;

    @FindBy(xpath = collectionPageSnackbarLocator)
    private WebElement collectionPageSnackbar;

    public CollectionsPage(WebDriver driver) {
        super(driver);
    }

    public DialogFrame clickCreateButton(){
        createButton.click();
        return new DialogFrame(driver);
    }

    public CollectionsPage clickCollectionDropdownMenuButton(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(collectionsGridLocator)));
        collectionsInGridList
                .get(1)
                .findElement(By.xpath(collectionDropdownMenuButtonLocator))
                .click();
        return new CollectionsPage(driver);
    }

    public DialogFrame selectCollectionDropdownMenuOption(String menuOption){
        collectionDropdownMenuButton.click();
        collectionDropdownMenu.stream().filter(o -> o.getText().contains(menuOption)).findFirst().get().click();
        return new DialogFrame(driver);
    }

    public int getCollectionsCount(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(collectionsGridLocator)));
        return collectionsInGridList.size();
    }

    public CollectionsPage checkSnackbarIsDisplay(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(collectionPageSnackbarLocator)));
        assertThat(driver.findElement(By.xpath(collectionPageSnackbarLocator)), isDisplayed());
        return new CollectionsPage(driver);
    }
}
