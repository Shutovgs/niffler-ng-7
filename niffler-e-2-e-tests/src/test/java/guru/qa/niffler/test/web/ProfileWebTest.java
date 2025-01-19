package guru.qa.niffler.test.web;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.BrowserExtension;
import guru.qa.niffler.jupiter.Category;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.page.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserExtension.class)
public class ProfileWebTest {
    private static final Config CFG = Config.getInstance();

    @Category(
        username = "duck",
        archived = false
    )
    @Test
    void archivedCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(CFG.defaultUser(), CFG.defaultPassword())
            .headerMenu.openProfilePage()
            .archiveCategory(category.name())
            .showArchived()
            .checkThatCategoryArchived(category.name());
    }

    @Category(
        username = "duck",
        archived = true
    )
    @Test
    void activeCategoryShouldPresentInCategoriesList(CategoryJson category) {
        Selenide.open(CFG.frontUrl(), LoginPage.class)
            .login(CFG.defaultUser(), CFG.defaultPassword())
            .headerMenu.openProfilePage()
            .showArchived()
            .unArchiveCategory(category.name())
            .checkThatCategoryUnArchived(category.name());
    }
}
