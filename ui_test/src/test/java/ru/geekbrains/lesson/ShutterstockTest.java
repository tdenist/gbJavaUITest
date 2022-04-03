package ru.geekbrains.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShutterstockTest
{
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
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
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        driver.get(BASE_URL);
    }


    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @Test
    void createCollectionTest(){

        // Нажать кнопку "Войти"
        driver.findElement(By.xpath("//a[@data-automation=\"loginButton\"]")).click();
        //В поле «Адрес электронной почты или имя пользователя» ввести e-mail
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"username\"]")));
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(EMAIL);
        // В поле «Пароль» ввести пароль
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(PASSWORD);
        // Нажать кнопку «Войти»
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        // Нажать на иконку "Каталог"
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-automation=\"utility-bar_catalog\"]")));
        driver.findElement(By.xpath("//a[@data-automation=\"utility-bar_catalog\"]")).click();
        // Выбрать пункт "Коллекции"
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-tour-stop=\"quick-access-collections\"]")));
        driver.findElement(By.xpath("//a[@data-tour-stop=\"quick-access-collections\"]")).click();
        //Запомнить количество имеющихся коллекций
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-track-section=\"collectionsHomeNav\"]/span[contains(@title, \"Изображения\")]")));
        int beforeCollectionsCount = Integer.parseInt(driver.findElement(By.xpath("//div[@data-track-section=\"collectionsHomeNav\"]/span[contains(@title, \"Изображения\")]"))
                .getText()
                .replaceAll("[^0-9]", ""));
        // Нажать кнопку "Создать"
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-track-label=\"createCollectionIcon\"]")));
        driver.findElement(By.xpath("//button[@data-track-label=\"createCollectionIcon\"]")).click();
        // Ввести название коллекции
        driver.findElement(By.xpath("//input[@data-automation=\"CollectionTitleInputDialog_input-active\"]")).sendKeys("test");
        // Нажать кнопку "Создать"
        driver.findElement(By.xpath("//button[@data-automation=\"CollectionTitleInputDialog_submit_button-active\"]")).click();

        // Проверка добавления новой коллекции:
        // 1. Внизу экрана появилось сообщени о создании коллекции
        // 2. Сравнить количество коллекций до и после добавления новой
        // Сразу посчитать количество коллекций не получается, они подгружаются постепенно, то появления сообщения.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-automation = \"CollectionPage_snackbar\"]")));
        int afterCollectionsCount = Integer.parseInt(driver.findElement(By.xpath("//div[@data-track-section=\"collectionsHomeNav\"]/span[contains(@title, \"Изображения\")]"))
                .getText()
                .replaceAll("[^0-9]", ""));
        Assert.assertEquals(beforeCollectionsCount + 1, afterCollectionsCount);
    }
}
