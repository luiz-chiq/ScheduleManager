package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPage {
    protected WebDriver driver;

    private By nameBy = By.id("name");
    private By emailBy = By.id("email");
    private By phoneBy = By.id("phone");
    private By buttonBy = By.cssSelector("input[type=\"submit\"][value=\"Salvar\"]");

    public EditPage(WebDriver driver) {
        this.driver = driver;
    }

    public EditPage editContact(String name, String email, String phone) {
        driver.findElement(nameBy).clear();
        driver.findElement(nameBy).sendKeys(name);
        driver.findElement(emailBy).clear();
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(phoneBy).clear();
        driver.findElement(phoneBy).sendKeys(phone);
        driver.findElement(buttonBy).click();
        return new EditPage(driver);
    }
}
