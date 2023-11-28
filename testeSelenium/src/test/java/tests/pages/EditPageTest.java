package tests.pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Contact;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditPageTest {
    private WebDriver driver;

    private static final String INDEX_URL = "http://127.0.0.1:5500/index.html";
    private static final String EDIT_URL = "http://127.0.0.1:5500/edit.html";

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    private void openIndexAndClickEdit() {
        driver.get(INDEX_URL);

        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(), 'Editar')]"));

        editButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.urlToBe(EDIT_URL));
    }

    @Test
    @DisplayName("Should alert an error as email is not in correct format")
    public void editContactWithIncorrectEmailFormat() {
        openIndexAndClickEdit();

        Faker faker = new Faker();
        EditPage editPage = new EditPage(driver);

        String name = faker.name().fullName();
        String phone = "+5516993388338";
        String email = name + ".com";


        editPage.editContact(name, email, phone);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        assertEquals("Email inválido!", alertText);
    }


    @Test
    @DisplayName("Should alert an error as phone is not in correct format")
    public void editContactWithIncorrectPhoneFormat() {
        openIndexAndClickEdit();

        Faker faker = new Faker();
        EditPage editPage = new EditPage(driver);

        String name = faker.name().fullName();
        String phone = "+55992841";
        String email = faker.internet().emailAddress();


        editPage.editContact(name, email, phone);

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        assertEquals("Telefone inválido!", alertText);
    }


}
