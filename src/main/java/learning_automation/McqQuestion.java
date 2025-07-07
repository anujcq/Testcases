package learning_automation;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class McqQuestion {
    WebDriver driver;

    public McqQuestion(WebDriver driver) {
        this.driver = driver;
    }

    public void multiChoiceQuestion() throws Exception {

        List<WebElement> mcqOptions = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='ant-radio']")));

        if (!mcqOptions.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(mcqOptions.size()); // generate random index
            WebElement randomOption = mcqOptions.get(randomIndex);
            randomOption.click();
        } else {
            System.out.println("No MCQ options found!");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class = 'app-logo']/button")).click();
    }

}
