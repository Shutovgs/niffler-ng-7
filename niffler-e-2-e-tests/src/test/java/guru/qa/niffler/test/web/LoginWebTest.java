package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.BrowserExtension;
import guru.qa.niffler.jupiter.Spending;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserExtension.class)
public class LoginWebTest {
    private static final Config CFG = Config.getInstance();

    @Spending(
        username = "duck",
        category = "Обучение",
        description = "Обучение Advanced 2.0",
        amount = 79990
    )
    @Test
    void mainPageShouldBeDisplayedAfterSuccessLogin(SpendJson spend) {
        MainPage mainPage = Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(CFG.defaultUser(), CFG.defaultPassword());

        mainPage.checkMainPage();
        mainPage.checkThatTableContainsSpending(spend.description());
    }

    @Test
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        final String wrongPassword = "1234";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
            .setUsername(CFG.defaultUser())
            .setPassword(wrongPassword)
            .checkThatOccurredError();
    }
}
