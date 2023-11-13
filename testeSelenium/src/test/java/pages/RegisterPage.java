package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    protected WebDriver driver;

    private By nameBy = By.id("name");
    private By emaildBy = By.id("email");
    private By phoneBy = By.id("phone");
    private By buttonBy = By.cssSelector("input[type=\"submit\"][value=\"Salvar\"]");
}
