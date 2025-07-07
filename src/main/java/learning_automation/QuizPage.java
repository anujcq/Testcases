package learning_automation;

import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuizPage {
    WebDriver driver;

    public QuizPage(WebDriver driver) {
        this.driver = driver;
    }

    public void quizData() throws Exception {

        int sectionCount = driver.findElements(By.xpath("//*[@class='dashboard-segment-container ' or @class='dashboard-segment-container active']")).size();

        for (int s = 0; s < sectionCount; s++) {
            List<WebElement> sections = driver.findElements(By.xpath(
                    "//*[@class='dashboard-segment-container ' or @class='dashboard-segment-container active'] "));
            WebElement cSection = sections.get(s);
            int totalQuestions = cSection.findElements(By.xpath(".//tbody/tr")).size();
            for (int q = 0; q < totalQuestions; q++) {
                CurrentSection currentSection = new CurrentSection(driver, s, q);
                currentSection.SolveAllQuestions();
            }
            
            {
                Thread.sleep(3000);

                WebElement section=driver.findElements(By.xpath(
                "//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']")).get(s);
                
                boolean SecSubmit = false;
                try{
                    SecSubmit = section.findElement(By.xpath(".//button/span[text()='submit this section']")).isDisplayed();
                }
                catch(Exception e){
                    System.out.println("Section revisit enabled");
                }
                    
                if(SecSubmit){
                    if(s!=sectionCount-1){
                        sectionSubmit(s);
                    }
                }
            }
        }
        testSubmit();
    }

    public void sectionSubmit(int s) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement section = driver.findElements(By.xpath(
                "//div[@class='dashboard-segment-container active' or @class='dashboard-segment-container ']")).get(s);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                section.findElement(By.xpath(".//button/span[text()='submit this section']"))));
        button.click();

        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//button/span[text()='yes']")));
        WebElement yesButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button/span[text()='yes']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesButton);

        String otp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-modal-body']//strong"))).getText();
        driver.findElement(By.xpath("//div[@class='ant-modal-body']//input[@placeholder='Enter Otp']")).sendKeys(otp);
        driver.findElement(By.xpath("//button/span[text()='Submit']")).click();

    }

    public void testSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button/span[text()='Submit Test']")))
                .click();
        ;

        WebElement yesButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button/span[text()='yes']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesButton);

        String otp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='ant-modal-body']//strong"))).getText();
        driver.findElement(By.xpath("//div[@class='ant-modal-body']//input[@placeholder='Enter Otp']")).sendKeys(otp);
        driver.findElement(By.xpath("//button/span[text()='Submit']")).click();
    }

}
