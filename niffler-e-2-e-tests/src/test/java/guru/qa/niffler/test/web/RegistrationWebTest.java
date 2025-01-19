package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.BrowserExtension;
import guru.qa.niffler.page.LoginPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.utils.DataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserExtension.class)
public class RegistrationWebTest {
    private static final Config CFG = Config.getInstance();

    @Test
    void shouldRegisterNewUser() {
        final String userName = DataGenerator.randomUserName();

        MainPage mainPage = Selenide.open(CFG.frontUrl(), LoginPage.class)
            .createNewAccount()
            .setUsername(userName)
            .setPassword(CFG.defaultPassword())
            .setPasswordSubmit(CFG.defaultPassword())
            .submitRegistration()
            .login(userName, CFG.defaultPassword());

        mainPage.checkMainPage();
        mainPage.checkThatNoSpendings();
    }

    @Test
    void shouldNotRegisterUserWithExistingUsername() {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
            .createNewAccount()
            .setUsername(CFG.defaultUser())
            .setPassword(CFG.defaultPassword())
            .setPasswordSubmit(CFG.defaultPassword())
            .checkThatOccurredError();
    }

    @Test
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        final String userName = DataGenerator.randomUserName();
        final String wrongPassword = "1234";

        Selenide.open(CFG.frontUrl(), LoginPage.class)
            .createNewAccount()
            .setUsername(userName)
            .setPassword(CFG.defaultPassword())
            .setPasswordSubmit(wrongPassword)
            .checkThatOccurredError();
    }
}
