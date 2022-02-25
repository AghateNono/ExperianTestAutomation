package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class CarInsuranceSteps {

    public WebDriver driver;

    @Given("user navigates to URL")
    public void userNavigatesToURL() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://creditmatcher.experian.co.uk/loans/search?LoanDetails.Purpose=DebtConsolidation");
        driver.manage().window().maximize();
        //To accept cookies
        driver.findElement(By.cssSelector("#ensCloseBanner")).click();
    }

    @When("user clicks on Car Insurance link on the footer")
    public void userClicksOnCarInsuranceButtonOnTheFooter() {
        driver.findElement(By.cssSelector("li.site-footer-navigation_item:nth-child(7) > a:nth-child(1)")).click();
    }

    @Then("Car Insurance page is displayed")
    public void carInsurancePageIsDisplayed() {
        // validation that car insurance page is displayed
        boolean isCarInsuranceDisplayed = driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/div/div/p")).isDisplayed();
        Assert.assertTrue(isCarInsuranceDisplayed, "Message is displayed");

        // close browser window & end webdriver session
        driver.quit();
    }
}
