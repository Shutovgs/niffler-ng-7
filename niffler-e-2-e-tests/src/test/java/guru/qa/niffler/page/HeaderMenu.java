package guru.qa.niffler.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

public class HeaderMenu {
    private final SelenideElement avatarButton = $("[data-testid='PersonIcon']");
    private final SelenideElement profileButton = $("a[href='/profile']");

    public void checkThatAvatarVisible() {
        avatarButton.should(visible);
    }

    public ProfilePage openProfilePage() {
        avatarButton.click();
        profileButton.click();
        return new ProfilePage();
    }
}
