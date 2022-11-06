package Functionality;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import pages.LoginPom;

public class Login {
    WebDriver driver = null ;
    LoginPom LP = new LoginPom();

    public static void main(String[] args) {

    }

    @Given("User open the browser")
    public void user_open_the_browser(){
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\Driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @When("navigate to the login page")
    public void navigation(){

        driver.navigate().to("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }

    @And("^User enter a valid email and password \"(.*)\" and \"(.*)\"$")
    public void enter(String email , String Pass){
        LP.username(driver).sendKeys(email);
        LP.password(driver).sendKeys(Pass);
    }

    @And("User click on login button")
    public void click() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div>button[type=\"submit\"]")).click();
        Thread.sleep(2000);

    }

    @Then("successfully message will displayed")
    public void successfully_message_will_displayed() throws InterruptedException {
        String Actual = driver.getCurrentUrl();
        String Expected = "https://demo.nopcommerce.com/";
        Assert.assertTrue(Actual.contains(Expected),"Error message = Wrong Input");
        Thread.sleep(4000);
        driver.quit();
    }


}
