package ru.geekbrains.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ShutterstockTest
{
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
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void createCollectionTest(){

        driver.get(BASE_URL);

        int beforeCollectionsCount =
                new MainPage(driver)
                .clickLoginButton()
                .login(EMAIL,PASSWORD)
                .clickCatalogButton()
                .clickCollectionsButton()
                .getCollectionsCount();

        int afterCollectionsCount =
                new CollectionsPage(driver)
                .clickCreateButton()
                .inputCollectionName("test")
                .clickCreateCollectionButton()
                .checkSnackbarIsDisplay()
                .getCollectionsCount();

        Assertions.assertEquals(beforeCollectionsCount + 1, afterCollectionsCount);
    }

    @Test
    void deleteCollectionTest(){

        driver.get(BASE_URL);

        int beforeCollectionsCount =
                new MainPage(driver)
                        .clickLoginButton()
                        .login(EMAIL,PASSWORD)
                        .clickCatalogButton()
                        .clickCollectionsButton()
                        .getCollectionsCount();

        int afterCollectionsCount = new CollectionsPage(driver)
                .clickCollectionDropdownMenuButton()
                .selectCollectionDropdownMenuOption("Удалить")
                .clickDeleteCollectionButton()
                .checkSnackbarIsDisplay()
                .getCollectionsCount();

        Assertions.assertEquals(beforeCollectionsCount - 1, afterCollectionsCount);

        String a ="";
    }
}
