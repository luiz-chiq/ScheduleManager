package pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegisterPageTest {

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
    SignInPage signInPage = new SignInPage(driver);
    HomePage homePage = signInPage.loginValidUser("userName", "password");
    assertThat(homePage.getMessageText(), is("Hello userName"));
  }
}
