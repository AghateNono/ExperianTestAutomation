package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoanConsolidationSteps {

    public WebDriver driver;

//    @Before
//    public void browserSetup(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://creditmatcher.experian.co.uk/loans/search?LoanDetails.Purpose=DebtConsolidation");
//        driver.manage().window().maximize();
//
//    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Given("user navigates to Experian website")
    public void userNavigatesToExperianWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://creditmatcher.experian.co.uk/loans/search?LoanDetails.Purpose=DebtConsolidation");
        driver.manage().window().maximize();
       //To accept cookies
        driver.findElement(By.cssSelector("#ensCloseBanner")).click();
    }

    @And("user enters the amount to borrow {string}")
    public void userEntersTheAmountToBorrow(String loanAmount) throws InterruptedException {
        driver.findElement(By.name("LoanDetails.Amount")).click();
        driver.findElement(By.xpath("//input[@placeholder='e.g. 7,500']")).sendKeys(loanAmount);
    }

    @And("user enters the loan repayment term {string}")
    public void userEntersTheLoanRepaymentTerm(String repaymentTerm) {
        driver.findElement(By.name("LoanDetails.Period")).click();
        driver.findElement(By.xpath("//input[@placeholder='e.g. 3']")).sendKeys(repaymentTerm);
    }

    @And("user clicks on Continue")
    public void userClicksOnContinue() {
        driver.findElement(By.xpath("//*[@id=\"search-form\"]/div[3]/div/div[3]/button/span")).click();
    }

    @When("user enters an invalid amount to borrow {string}")
    public void userEntersAnInvalidAmountToBorrow(String amountToBorrow) {
        driver.findElement(By.name("LoanDetails.Amount")).click();
        driver.findElement(By.xpath("//input[@placeholder='e.g. 7,500']")).sendKeys(amountToBorrow);
    }

    @Then("an error message is displayed for less than 500")
    public void anErrorMessageIsDisplayedForLessThan500() {
        boolean warningMessage = driver.findElement(By.xpath("//div[contains(text(), 'Please enter an amount between £500 and £50,000 to the nearest pound')]")).isDisplayed();
        Assert.assertTrue(warningMessage,"Warning message is present");
    }

    @Then("an error message is displayed for more than 50000")
    public void anErrorMessageIsDisplayedForMoreThan50000() {
        boolean warningMessage = driver.findElement(By.xpath("//div[contains(text(), 'Please enter an amount between £500 and £50,000 to the nearest pound. More than £50,000 requires home ownership secured by a mortgage.')]")).isDisplayed();
        Assert.assertTrue(warningMessage,"Warning message is present");
    }

    @Then("an error message is displayed for more than 30")
    public void anErrorMessageIsDisplayedForMoreThan30() {
        boolean warningMessage = driver.findElement(By.xpath("//div[contains(text(), 'Please enter an amount between 1 and 30')]")).isDisplayed();
        Assert.assertTrue(warningMessage,"Warning message is present");
    }
}
