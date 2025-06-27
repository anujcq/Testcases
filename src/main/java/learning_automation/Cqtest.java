package learning_automation;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 */
public class Cqtest {
    public static void main(String[] args) {

        System.out.println("Enter number of emails to be created:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            String email = "";
            int nameLength = (int) (Math.random() * 3) + 3; // 3 to 5 characters
            for (int j = 0; j < nameLength; j++) {
                char randomChar = (char) ('a' + (int) (Math.random() * 26)); // 'a' to 'z'
                email += randomChar;
            }

            int numLength = (int) (Math.random() * 6) + 5; // 5 to 10 digits
            for (int j = 0; j < numLength; j++) {
                int randomDigit = (int) (Math.random() * 10); // 0 to 9
                email += randomDigit;
            }
            arr[i] = email;

        }

        for (int i = 0; i < n; i++) {
            WebDriver driver = new ChromeDriver();
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.get("https://tests.cqtestga.com/test/1750932516604");
                driver.manage().window().maximize();
                driver.findElement(By.xpath("//span[text()='sign up']")).click();
                driver.findElement(By.id("name")).sendKeys(arr[i]);
                driver.findElement(By.id("email")).sendKeys(arr[i] + "@gmail.com");
                driver.findElement(By.id("password")).sendKeys("Master@123");
                driver.findElement(By.id("quizCode")).sendKeys("1");
                driver.findElement(By.id("mobile")).sendKeys("1234567890");
                driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
                Thread.sleep(5000);
                
                driver.findElement(By.xpath("//div[@class='start-quiz-container']/button")).click();
                System.out.println("pahunche");
                Thread.sleep(5000);
                //  List<WebElement> questions = driver.findElements(By.xpath("//tbody/tr"));
                // for(WebElement quest: questions){
                //     questions.get(0).findElement(By.xpath("//td[2]")).click();
                //     Thread.sleep(20000);
                // }
            
            } catch (Exception e) {
                System.out.println("Error for user " + arr[i] + ": " + e.getMessage());
            } finally {
                    // driver.quit();
                }
            
        }
        scan.close();
    }
}
