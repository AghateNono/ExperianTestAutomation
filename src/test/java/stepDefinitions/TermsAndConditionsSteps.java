package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;

public class TermsAndConditionsSteps {

    public WebDriver driver;

    @Given("user navigates to the URL")
    public void userNavigatesToTheURL() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://creditmatcher.experian.co.uk/loans/search?LoanDetails.Purpose=DebtConsolidation");
        driver.manage().window().maximize();
        //To accept cookies
        driver.findElement(By.cssSelector("#ensCloseBanner")).click();

    }

    @When("user clicks on the Terms & Conditions link on the footer")
    public void userClicksOnTheTermsConditionsLinkOnTheFooter() {
        driver.findElement(By.cssSelector("li.site-footer-navigation_item:nth-child(11) > a:nth-child(1)")).click();
    }

    @Then("Terms & Conditions page is displayed with heading {string}")
    public void termsConditionsPageIsDisplayedWithHeading(String heading) {
        // switching focus of webdriver to new tab
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

        // validating the terms & condition page is displayed
        String expectedHeading = driver.findElement(By.xpath("//h1[@class = 'h1-colour mt-20']")).getText();
        Assert.assertEquals(heading, expectedHeading);

        driver.quit();
    }
}
