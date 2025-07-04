package learning_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Signup {
    WebDriver driver;

    public Signup(WebDriver driver, int n) {
        this.driver = driver;
    }

    // Generate  random email

    public String[] generateRandomEmail(int n) throws Exception {
        String arr[]=new String[n];

        for(int i =0; i<n;i++){
            
            String email = "";

            int nameLength = (int) (Math.random() * 3) + 3; // 3 to 5 letters
            for (int j = 0; j < nameLength; j++) {
                char randomChar = (char) ('a' + (int) (Math.random() * 26));
                email += randomChar;
            }

            int numLength = (int) (Math.random() * 6) + 5; // 5 to 10 digits
            for (int j = 0; j < numLength; j++) {
                int randomDigit = (int) (Math.random() * 10);
                email += randomDigit;
            }
            arr[i]=email;
        }
        return arr;
    }
    public void AutoSignUp(WebDriver driver,String arr[],int i) throws Exception{
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                driver.get("https://tests.cqtestga.com/test/1750932516604");
                driver.manage().window().maximize();
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='sign up']"))).click();
                driver.findElement(By.id("name")).sendKeys(arr[i]);
                driver.findElement(By.id("email")).sendKeys(arr[i] + "@gmail.com");
                driver.findElement(By.id("password")).sendKeys("Master@123");
                driver.findElement(By.id("quizCode")).sendKeys("1");
                driver.findElement(By.id("mobile")).sendKeys("1234567890");

                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type=\"submit\"]"))).click();
                Thread.sleep(2000);


                wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'start-quiz-container')]/button"))).click();
    }
}
