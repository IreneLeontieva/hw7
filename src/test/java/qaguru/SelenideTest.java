package qaguru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    private static final String URL = "https://github.com";
    private static final String REPOSITORY = "IreneLeontieva/hw7";
    private static final int ISSUE_NUMBER = 1;

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(URL);

        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY).pressEnter();

        $(By.linkText(REPOSITORY)).click();
        $(By.partialLinkText("Issues")).click();
        $(withText("#" + ISSUE_NUMBER)).should(Condition.visible);
    }

}