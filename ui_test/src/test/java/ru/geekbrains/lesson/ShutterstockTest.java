package ru.geekbrains.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.geekbrains.lesson.pages.CollectionsPage;
import ru.geekbrains.lesson.pages.MainPage;

@Feature("Работа с коллекциями")
public class ShutterstockTest {
    WebDriver driver;

    private static final String BASE_URL = "http://www.shutterstock.com";
    private static final String EMAIL = "DenisAutotest@yandex.ru";
    private static final String PASSWORD = "AutoUI2022";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // иначе при меньшем размере по умолчанию не видно кнопку "Войти"
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver(options));
    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log : logEntries) {
            Allure.addAttachment("Лог браузера:", log.getMessage());
        }
        driver.quit();
    }

    @Test
    @Story("Проверка создания коллекции")
    void createCollectionTest() {

        driver.get(BASE_URL);

        int beforeCollectionsCount =
                new MainPage()
                        .clickLoginButton()
                        .login(EMAIL, PASSWORD)
                        .clickCatalogButton()
                        .clickCollectionsButton()
                        .getCollectionsCount();

        int afterCollectionsCount =
                new CollectionsPage()
                        .clickCreateButton()
                        .inputCollectionName("test")
                        .clickCreateCollectionButton()
                        .checkSnackbarIsDisplay()
                        .getCollectionsCount();

        Assertions.assertEquals(beforeCollectionsCount + 1, afterCollectionsCount);
    }

    @Test
    @Story("Проверка удаления коллекции")
    void deleteCollectionTest() {

        driver.get(BASE_URL);


        int beforeCollectionsCount = new MainPage()
                .clickLoginButton()
                .login(EMAIL, PASSWORD)
                .clickCatalogButton()
                .clickCollectionsButton()
                .getCollectionsCount();

        int afterCollectionsCount = new CollectionsPage()
                .selectCollectionDropdownMenuOption("Удалить")
                .clickDeleteCollectionButton()
                .checkSnackbarIsDisplay()
                .getCollectionsCount();

        Assertions.assertEquals(beforeCollectionsCount - 1, afterCollectionsCount);
    }
}
