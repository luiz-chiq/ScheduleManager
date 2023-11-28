package tests.pages;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.VerifyIndex;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {

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
        driver.get("http://127.0.0.1:5500/index.html");



//            String selector = String.format("#contacts # %s .del-btn", "4");
//            WebElement deleteButton = driver.findElement(By.cssSelector(selector));
//            new WebDriverWait(driver, Duration.ofSeconds(2))
//                    .until(ExpectedConditions.alertIsPresent());
//            deleteButton.click();
//            Alert alert = driver.switchTo().alert();
//            String alertText = alert.getText();
//            System.out.println(alertText);
//            alert.accept();
//
//            assertEquals("Telefone inválido!", alertText);

    }

}

