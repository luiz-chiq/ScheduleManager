package tests.pages;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.VerifyIndex;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexPageTest {




    @Test
    @DisplayName("Should find the contact")
    public void verifyIfContactExists() {
        assertTrue(VerifyIndex.verifyIfContactExistsByName("Maria da Silva"));
    }
}
