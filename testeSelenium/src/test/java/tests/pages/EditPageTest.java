package tests.pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.Contact;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Nested
    @DisplayName("Name Validation Tests")
    class NameValidationTests {
        @Test
        @DisplayName("Should alert an error as name field is empty")
        public void editContactWithEmptyName() {
            openIndexAndClickEdit();

            Faker faker = new Faker();
            EditPage editPage = new EditPage(driver);

            String name = "";
            String phone = "+5516993388338";
            String email = faker.internet().emailAddress();


            editPage.editContact(name, email, phone);

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println(alertText);
            alert.accept();

            assertEquals("Nome inválido!", alertText);
        }

        @Test
        @DisplayName("Should alert an error as name field is only space characters")
        public void editContactWithSpaceOnlyName() {
            openIndexAndClickEdit();

            Faker faker = new Faker();
            EditPage editPage = new EditPage(driver);

            String name = "   ";
            String phone = "+5516993388338";
            String email = faker.internet().emailAddress();


            editPage.editContact(name, email, phone);

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println(alertText);
            alert.accept();

            assertEquals("Nome inválido!", alertText);
        }

        @Test
        @DisplayName("Should alert an error as name field has invalid characters")
        public void editContactWithInvalidCharactersName() {
            openIndexAndClickEdit();

            Faker faker = new Faker();
            EditPage editPage = new EditPage(driver);

            String name = "-**^%$$ d4%";
            String phone = "+5516993388338";
            String email = faker.internet().emailAddress();


            editPage.editContact(name, email, phone);

            try {
                Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.alertIsPresent());
                String alertText = alert.getText();
                System.out.println(alertText);
                alert.accept();
            } catch (TimeoutException e) {
                fail("The expected alert error message has not been displayed.");
            }
        }
    }

    @Nested
    @DisplayName("Email Validation Tests")
    class EmailValidationTests {
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
        @DisplayName("Should alert an error as email is empty")
        public void editContactWithEmptyEmail() {
            openIndexAndClickEdit();

            Faker faker = new Faker();
            EditPage editPage = new EditPage(driver);

            String name = faker.name().fullName();
            String phone = "+5516993388338";
            String email = "";


            editPage.editContact(name, email, phone);

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println(alertText);
            alert.accept();

            assertEquals("Email inválido!", alertText);
        }
    }

    @Nested
    @DisplayName("Phone Validation Tests")
    class PhoneValidationTests {
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

}
