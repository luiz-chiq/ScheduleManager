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
    void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
    }
    @AfterEach
    void tearDown(){
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
        Faker faker = new Faker();

        String name = faker.name().fullName();
        String phone = faker.phoneNumber().toString();
        String email = faker.internet().emailAddress();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registerNewContact(name,email,phone);
        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        final String alertText = alert.getText();
        alert.accept();
        assertEquals("Contato salvo com sucesso!", alertText);
    }
}
