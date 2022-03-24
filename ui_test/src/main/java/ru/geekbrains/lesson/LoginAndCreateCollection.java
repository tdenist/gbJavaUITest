package ru.geekbrains.lesson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class LoginAndCreateCollection
{
    public static void main( String[] args ) throws InterruptedException {

        final String EMAIL = "DenisAutotest@yandex.ru";
        final String PASSWORD = "AutoUI2022";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // иначе при меньшем размере по умолчанию не видно кнопку "Войти"
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Открыть сайт
        driver.get("https://www.shutterstock.com/");
        // Нажать кнопку "Войти"
        driver.findElement(By.xpath("//a[@data-automation=\"loginButton\"]")).click();
        //В поле «Адрес электронной почты или имя пользователя» ввести e-mail
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(EMAIL);
        // В поле «Пароль» ввести пароль
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(PASSWORD);
        // Нажать кнопку «Войти»
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();

        // Нажать на иконку "Каталог"
        driver.findElement(By.xpath("//a[@data-automation=\"utility-bar_catalog\"]")).click();
        // Выбрать пункт "Все ваши коллекции"
        driver.findElement(By.xpath("//a[@data-tour-stop=\"quick-access-collections\"]")).click();
        // Нажать кнопку "Создать"
        driver.findElement(By.xpath("//button[@data-track-label=\"createCollectionIcon\"]")).click();
        // Ввести название коллекции
        driver.findElement(By.xpath("//input[@data-automation=\"CollectionTitleInputDialog_input-active\"]")).sendKeys("test");
        // Нажать кнопку "Создать"
        driver.findElement(By.xpath("//button[@data-automation=\"CollectionTitleInputDialog_submit_button-active\"]")).click();

        Thread.sleep(5000);

        driver.quit();
    }
}
