package tests.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

public class EditPageTest {
    private WebDriver driver;

    private static final String INDEX_URL = "http://127.0.0.1:5500/index.html";
    private static final String EDIT_URL = "http://127.0.0.1:5500/edit.html";
    private static final int CONTACT_ID = 1;

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

        String seletorCss = "#contacts .item#" + CONTACT_ID;

        WebElement item = driver.findElement(By.cssSelector(seletorCss));

        if (item != null) {
            WebElement editButton = item.findElement(By.xpath("//button[contains(text(), 'Editar')]"));
            editButton.click();
        }

    }
}
