package guru.qa.niffler.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

public class ProfilePage {
    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement nameInput = $("#name");
    private final SelenideElement newCategoryInput = $("#category");
    private final SelenideElement saveButton = $("button[type='submit']");
    private final SelenideElement showArchivedCheckBox = $("input[type='checkbox']");
    private final SelenideElement archiveCategoryButton = $("button[aria-label='Archive category']");
    private final SelenideElement editCategoryButton = $("button[aria-label='Edit category']");
    private final SelenideElement unArchiveCategoryButton = $("[data-testid='UnarchiveOutlinedIcon']");
    private final SelenideElement archiveButton = $(byText("Archive"));
    private final SelenideElement unArchiveButton = $(byText("Unarchive"));

    public SelenideElement category(String categoryName) {
        return $(byText(categoryName));
    }

    public ProfilePage showArchived() {
        showArchivedCheckBox.click();
        return this;
    }

    public ProfilePage archiveCategory(String categoryName) {
        category(categoryName).parent().parent().find("button[aria-label='Archive category']").click();
        archiveButton.click();
        return this;
    }

    public ProfilePage unArchiveCategory(String categoryName) {
        category(categoryName).parent().parent().find("[data-testid='UnarchiveOutlinedIcon']").click();
        unArchiveButton.click();
        return this;
    }

    public void checkThatCategoryArchived(String categoryName) {
        category(categoryName).parent().parent().find("[data-testid='UnarchiveOutlinedIcon']").should(visible);
    }

    public void checkThatCategoryUnArchived(String categoryName) {
        category(categoryName).parent().parent().find("button[aria-label='Archive category']").should(visible);
    }
}
