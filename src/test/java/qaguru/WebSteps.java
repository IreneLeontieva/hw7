package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class WebSteps {

    private static final String URL = "https://github.com";
    private static final String REPOSITORY = "IreneLeontieva/hw7";
    private static final int ISSUE_NUMBER = 1;

    //1st comment
    @Step("Open main page {mainPage}")
    public void openMainPage(String mainPage) {
        open(mainPage);
    }

    //2st comment
    @Step("Search for the repo {searchRepo}")
    public void searchRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repo).pressEnter();
    }

    //2st comment
    @Step("Go the repo {searchRepo}")
    public void goToRepo(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step("Go the issues")
    public void goToIssues() {
        $(By.partialLinkText("Issues")).click();
    }

    @Step("Check issue with number #{number}")
    public void goToIssueNumber(int number) {
        $(withText("#" + number)).should(Condition.visible);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        openMainPage(URL);
        searchRepo(REPOSITORY);
        goToRepo(REPOSITORY);
        goToIssues();
        goToIssueNumber(ISSUE_NUMBER);
        takeScreenshot();
    }
}
