package learning_automation.Tests;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import learning_automation.QuizPage;
import learning_automation.Signup;

/**
 * Unit test for simple App.
 */
public class AppTest {

public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of emails to be created:");
        // int n = scan.nextInt();
        int n = 1;
        WebDriver driver = null;

        for(int i =0;i<n;i++){
        try{
                driver = new ChromeDriver();
                Signup signup = new Signup(driver,n);
                String arr[]=signup.generateRandomEmail(n);
                signup.AutoSignUp(driver, arr, i);
                Thread.sleep(1000);

                QuizPage quizPage = new QuizPage(driver);
                quizPage.quizData();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally{
                // driver.quit();
            }
            
        }
}
}
