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

    public static Optional<Contact> getContactInfo(int id){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:5500/index.html");

        String seletorCss = "#contacts .item#" + id;

        WebElement item = driver.findElement(By.cssSelector(seletorCss));

        if (item != null) {
            WebElement nomeElement = item.findElement(By.tagName("span"));
            WebElement emailElement = item.findElement(By.xpath(".//p[contains(text(),'Email')]"));
            WebElement telefoneElement = item.findElement(By.xpath(".//p[contains(text(),'Telefone')]"));

            String name = nomeElement.getText();
            String email = emailElement.getText().replace("Email: ", "");
            String phoneNumber = telefoneElement.getText().replace("Telefone: ", "");

            System.out.println("Nome: " + name);
            System.out.println("Email: " + email);
            System.out.println("Telefone: " + phoneNumber);
            return Optional.of(new Contact(name, email, phoneNumber));
        }
        driver.quit();
        return Optional.empty();
    }

    public static String getFirstContactName() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        driver.get("http://127.0.0.1:5500/index.html");

        WebElement div = driver.findElement(By.className("item"));

        WebElement nomeElement = div.findElement(By.tagName("span"));
        String contactNme = nomeElement.getText();
        driver.quit();
        return contactNme;
    }
}
