import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.geekbrains.lesson.pages.*;

import static com.codeborne.selenide.Selenide.open;

public class MyStepdefs {

    private static final String BASE_URL = "http://www.shutterstock.com";
    private static final String EMAIL = "DenisAutotest@yandex.ru";
    private static final String PASSWORD = "AutoUI2022";

        @Given("Пользователь авторизовался на сайте")
        public void userAuthorized(){
            open(BASE_URL);
            new MainPage()
                    .clickLoginButton()
                    .login(EMAIL, PASSWORD);
        }

        @When("я нажимаю кнопку Каталог")
        public void clickCatalogueButton(){
            new MainPage()
                    .clickCatalogButton();
        }

        @And("^я нажимаю кнопку Коллекции$")
        public void clickCollectionsButton(){
            new CatalogPage()
                    .clickCollectionsButton();

        }
        @And("^я считаю количество коллекций$")
        public void countCollections(){
            new CollectionsPage()
                    .getCollectionsCount();
        }
        @And("^я нажимаю кнопку Создать$")
        public void clickCreateButton(){
            new CollectionsPage()
                    .clickCreateButton();
        }

        @And("^я ввожу название коллекции$")
        public void inputCollectionName(){
            new DialogFrame()
                    .inputCollectionName("test");

        }
        @And("^я нажимаю кнопку создания коллекции$")
        public void clickCreateCollectionButton(){
            new DialogFrame()
                    .clickCreateCollectionButton();
        }
        @Then("^появляется сообщение$")
        public void checkSnackbarIsDisplay(){
            new CollectionsPage()
                    .checkSnackbarIsDisplay();
        }

    @And("я выбира в выпадающем меню у коллекции Удалить")
    public void clickDropdownMenu() {
            new CollectionsPage()
                    .selectCollectionDropdownMenuOption("Удалить");
    }

    @And("я подтверждаю удаление")
    public void clickDeleteButton() {
            new DialogFrame()
                    .clickDeleteCollectionButton();
    }

    @After(value = "@close")
    public void quitBrowser() {
        Selenide.closeWebDriver();
    }
}