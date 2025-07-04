package learning_automation;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SubjectiveQuestion {
    WebDriver driver;

    public SubjectiveQuestion(WebDriver driver) {
        this.driver = driver;
    }

    public void subjectiveQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.ql-editor")));
        editor.click();
        editor.sendKeys("This is subjective question");

        try {
            String fileName = "src/test/resources/file-sample_PDF_1MB.pdf";
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("File not found: ");
            } else {
                driver.findElement(By.id("upldFile")).sendKeys(file.getAbsolutePath());
            }

        } catch (Exception e) {
            System.out.println("Upload elements not found: ");
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();
        driver.findElement(By.xpath("//div[@class = 'app-logo']/button")).click();

    }

}
