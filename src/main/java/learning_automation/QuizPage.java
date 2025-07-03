package learning_automation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
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

        List<WebElement> sections = driver.findElements(By.xpath("//*[@class=\"dashboard-segment-container \"]"));

        for (WebElement section : sections) {

            List<WebElement> questions = driver.findElements(By.xpath("//*[@class=\"ant-table-tbody\"]/tr"));
            System.out.println(questions.size());
            for (WebElement question : questions) {
                String questionType = question.findElement(By.xpath(".//td[3]")).getText();
                switch (questionType) {
                    case "MCQ": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        mcqQuestion();
                        return;
                    }
                    case "Web": {
                        break;
                    }
                    case "Subjective": {
                        question.findElement(By.xpath(".//td[2]/a")).click();
                        subjectiveQuestion();
                        break;
                    }
                    case "MQ": {
                        break;
                    }
                    case "Coding": {
                        break;
                    }
                }
            }
        }
    }

    public void mcqQuestion() throws Exception {

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
    }

    public void webQuestion() {

    }

    public void subjectiveQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editor = wait.until(ExpectedConditions.presenceOfElementLocated(
        By.cssSelector("div.ql-editor")));
        editor.click();
        editor.sendKeys("This is subjective question");

        try {
            String fileName = "C:/Users/HP/Desktop/file-sample_PDF_1MB.pdf";
            driver.findElement(By.id("upldFile")).sendKeys(fileName);;

        } catch (Exception e) {
            System.out.println("Upload elements not found: " + e.getMessage());
        }
        driver.findElement(By.xpath("//button/span[text()='submit']")).click();

    }

    public void mqQuestion() {

    }

    public void coding() {

    }

}
