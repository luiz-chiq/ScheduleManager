package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    protected WebDriver driver;

    private By nameBy = By.id("name");
    private By emailBy = By.id("email");
    private By phoneBy = By.id("phone");
    private By buttonBy = By.cssSelector("input[type=\"submit\"][value=\"Salvar\"]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;

    }

    public RegisterPage registerNewContact(String name, String email, String phone) {
        driver.findElement(nameBy).sendKeys(name);
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(phoneBy).sendKeys(phone);
        driver.findElement(buttonBy).click();
        return new RegisterPage(driver);
    }
}
