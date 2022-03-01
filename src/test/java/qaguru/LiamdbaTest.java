package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LiamdbaTest {

    private static final String URL = "https://github.com";
    private static final String REPOSITORY = "IreneLeontieva/hw7";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open " + URL, () -> {
            open(URL);
        });

        step("Search for " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY).pressEnter();
        });

        step("Go to " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Open issues", () -> {
            $(By.partialLinkText("Issues")).click();
        });

        step("Check issue with number " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
        });
    }

}