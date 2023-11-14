package pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Should open and close firefox browser")
    void shouldOpenAndCloseChromeBrowser() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://scl.ifsp.edu.br");
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    @DisplayName("Should register a new contact with name, email and phone completed")
    public void registerContact() {
        driver.get("http://127.0.0.1:5500/src/register.html");

        Faker faker = new Faker();

        String name = faker.name().fullName();
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

        assertEquals("Contato salvo com sucesso!", alertText);
    }

    @Test
    @DisplayName("Should not register a new contact with name, email but no phone number")
    public void registerContactWithoutPhoneNumber() {
        driver.get("http://127.0.0.1:5500/src/register.html");

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
}
