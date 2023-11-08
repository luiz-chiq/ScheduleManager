import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void aaaaa() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://scl.ifsp.edu.br");
        Thread.sleep(1000); // waits for 1s.
        WebElement campoDePesquisa = driver.findElement(By.id("portal-searchbox-field"));
        campoDePesquisa.sendKeys("Núcleo Docente Estruturante");
        Thread.sleep(500); // waits for 1s.
        campoDePesquisa.submit();

        Thread.sleep(3000); // waits for 1s.
        WebElement primeiraDivTileItem = driver.findElement(By.cssSelector("div.tileItem"));

// Agora, dentro da primeira div, localize o primeiro h2 e, em seguida, localize o link (por exemplo, uma âncora <a>)
        WebElement segundoDiv = primeiraDivTileItem.findElement(By.tagName("div"));
        WebElement primeiroH2 = segundoDiv.findElement(By.tagName("h2"));
        WebElement linkParaClicar = primeiroH2.findElement(By.tagName("a"));

// Clique no link
        linkParaClicar.click();
        Thread.sleep(3000); // waits for 1s.
        Thread.sleep(3000); // waits for 1s.



        driver.quit();
    }
}