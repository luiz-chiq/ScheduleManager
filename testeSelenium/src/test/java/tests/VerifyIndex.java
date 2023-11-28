package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Optional;

public class VerifyIndex {

    private static WebDriver driver;
    public static boolean verifyIfContactExistsByName(String name){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:5500/index.html");

        List<WebElement> divs = driver.findElements(By.className("item"));

        for (WebElement div : divs) {
            WebElement nomeElement = div.findElement(By.tagName("span"));
            String contactNme = nomeElement.getText();
            if (contactNme.equals(name)) {
                driver.quit();
                return true;
            }
        }
        driver.quit();
        return false;
    }
}
