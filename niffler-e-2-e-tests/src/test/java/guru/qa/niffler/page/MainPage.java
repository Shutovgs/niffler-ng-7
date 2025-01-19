package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

public class MainPage {
  private final ElementsCollection tableRows = $("#spendings tbody").$$("tr");
  private final SelenideElement statistics = $("canvas");
  private final SelenideElement noSpendings = $(byText("There are no spendings"));
  public HeaderMenu headerMenu = new HeaderMenu();

  public EditSpendingPage editSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).$$("td").get(5).click();
    return new EditSpendingPage();
  }

  public void checkThatTableContainsSpending(String spendingDescription) {
    tableRows.find(text(spendingDescription)).should(visible);
  }

  public void checkMainPage() {
    statistics.should(visible);
    headerMenu.checkThatAvatarVisible();
  }

  public void checkThatNoSpendings() {
    noSpendings.should(visible);
  }
}
