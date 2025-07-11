package learning_automation;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentSection {
    WebDriver driver;
    int secCount, qCount;

    public CurrentSection(WebDriver driver, int s, int q) {
        this.driver = driver;
        secCount = s;
        qCount = q;
    }

    public void SolveAllQuestions() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> sections = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//*[@class='dashboard-segment-container ' or @class='dashboard-segment-container active'] ")));
            System.out.println("sec size "+ sections.size());

        WebElement currentSection = sections.get(secCount);
        wait.until(ExpectedConditions.visibilityOf(currentSection));

        List<WebElement> questions = currentSection.findElements(By.xpath(".//tbody/tr"));
        System.out.println(questions.size());

        WebElement currentQuestionSolve = questions.get(qCount);
        String questionType = currentQuestionSolve.findElement(By.xpath(".//td[3]")).getText();

        switch (questionType) {
            case "MCQ": {
                currentQuestionSolve.findElement(By.xpath(".//td[2]/a")).click();
                McqQuestion mcqQuestion = new McqQuestion(driver);
                mcqQuestion.multiChoiceQuestion();
                break;
            }
            case "Web": {
                currentQuestionSolve.findElement(By.xpath(".//td[2]/a")).click();
                WebQuestion webQuestion = new WebQuestion(driver);
                webQuestion.webQuestion();
                break;
            }
            case "Subjective": {
                currentQuestionSolve.findElement(By.xpath(".//td[2]/a")).click();
                SubjectiveQuestion subjectiveQuestion = new SubjectiveQuestion(driver);
                subjectiveQuestion.subjectiveQuestion();
                break;
            }
            case "MQ": {
                currentQuestionSolve.findElement(By.xpath(".//td[2]/a")).click();
                MqQuestion mqQuestion = new MqQuestion(driver);
                mqQuestion.mqQuestion();
                break;
            }
            case "Coding": {
                currentQuestionSolve.findElement(By.xpath(".//td[2]/a")).click();
                CodingQuestion codingQuestion = new CodingQuestion(driver);
                codingQuestion.coding();
                break;
            }
            default:
                System.out.println("Select question type correctly");
                break;
        }

    }
}
