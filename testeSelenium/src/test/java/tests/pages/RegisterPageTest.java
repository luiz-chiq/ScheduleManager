package tests.pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterPageTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


    @Test
    @DisplayName("Should not register a new contact with name, email but no phone number")
    public void registerContactWithoutPhoneNumber() {
        driver.get("http://127.0.0.1:5500/register.html");

        Faker faker = new Faker();

        String name = faker.name().fullName();
        String phone = "";
        String email = faker.internet().emailAddress();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerNewContact(name, email, phone);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        assertEquals("Telefone inválido!", alertText);
    }

    @Test
    @DisplayName("Should not register a new contact with a one letter name")
    public void registerContactWithOneLetterName() {
        driver.get("http://127.0.0.1:5500/register.html");

        Faker faker = new Faker();

        String name = "N";
        String phone = "+5516992588088";
        String email = faker.internet().emailAddress();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerNewContact(name, email, phone);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        assertEquals("Nome inválido!", alertText);
    }
}
