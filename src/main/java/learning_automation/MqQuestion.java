package learning_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MqQuestion {
    WebDriver driver;

    public MqQuestion(WebDriver driver) {
        this.driver = driver;
    }

    public void mqQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> textAreas = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='ant-form-item-control-input-content']/span/textarea")));

        for (WebElement textArea : textAreas) {
            textArea.sendKeys("T");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class = 'app-logo']/button")).click();

    }

}
