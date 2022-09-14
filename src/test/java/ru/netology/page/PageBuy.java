package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class PageBuy {
    public PageBuy() {
        SelenideElement pageBuy = $x("//*[text()='Оплата по карте']");
        pageBuy.shouldBe(visible);
    }
    private ElementsCollection form = $$(".form-field .input");
    private SelenideElement numberCard = form.find(exactText("Номер карты")).$(".input__control");
    private SelenideElement month = form.find(exactText("Месяц")).$(".input__control");
    private SelenideElement year = form.find(exactText("Год")).$(".input__control");
    private SelenideElement holder = form.find(exactText("Владелец")).$(".input__control");
    private SelenideElement code = form.find(exactText("CVC/CVV")).$(".input__control");
    private SelenideElement buttonSent = $$("[type='button']").find(exactText("Продолжить"));

    private ElementsCollection formBelow = $$(".form-field .input .input__top");
    private SelenideElement belowFieldCard = formBelow.find(exactText("Номер карты")).parent().$(".input__sub");
    private SelenideElement belowFieldOwner = formBelow.find(exactText("Владелец")).parent().$(".input__sub");
    private SelenideElement belowFieldYear = formBelow.find(exactText("Год")).parent().$(".input__sub");
    private SelenideElement belowFieldMonth = formBelow.find(exactText("Месяц")).parent().$(".input__sub");
    private SelenideElement belowFieldCode = formBelow.find(exactText("CVC/CVV")).parent().$(".input__sub");

    private SelenideElement messageError = $(byText("Ошибка"));
    private SelenideElement messageDecline = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement messageSuccess = $(byText("Успешно"));
    private SelenideElement messageApprove = $(byText("Операция одобрена Банком."));



    public void declinedMessage() {
        SelenideElement message = $(byText("Ошибка")).shouldBe(visible, Duration.ofSeconds(10));
        SelenideElement messageSecond = $(byText("Ошибка! Банк отказал в проведении операции.")).shouldBe(visible, Duration.ofSeconds(10));
    }

    public void approvedMessage() {
        SelenideElement message = $(byText("Успешно")).shouldBe(visible, Duration.ofSeconds(10));
        SelenideElement messageSecond = $x("//*[text()='Операция одобрена Банком.']").shouldBe(visible, Duration.ofSeconds(10));
    }

    public void emptyFieldCardMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Номер карты"));
        first.parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void emptyFieldMonthMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Месяц"));
        first.parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void emptyFieldYearMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Год"));
        first.parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void emptyFieldHolderMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Владелец"));
        first.parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void emptyFieldCodeMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("CVC/CVV"));
        first.parent().$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    public void wrongFieldCardMessage() {
        SelenideElement first = $x("//*[text()='Номер карты']");
        first.parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void wrongFieldMonthMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Месяц"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void wrongFieldYearMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Год"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void wrongFieldHolderMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Владелец"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void wrongFieldCodeMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("CVC/CVV"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверный формат"));
    }

    public void validityMonthMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Месяц"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверно указан срок действия карты"));
    }

    public void validityYearMessage() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Год"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверно указан срок действия карты"));
    }

    public void expiredCardMessageYear() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Год"));
        first.parent().$(".input__sub").shouldHave(exactText("Истёк срок действия карты"));
    }

    public void expiredCardMessageMonth() {
        SelenideElement first = $$(".form-field .input .input__top").find(exactText("Месяц"));
        first.parent().$(".input__sub").shouldHave(exactText("Неверно указан срок действия карты"));
    }

    public void toSent() {
        SelenideElement buttonSent = $x("//*[text()='Продолжить']");
        buttonSent.click();
    }

    public void fillBuyForm(DataHelper.FormFields info) {
        numberCard.setValue(info.getNumberCard());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        holder.setValue(info.getHolder());
        code.setValue(info.getCode());
    }

    public void cleanFieldsForm() {
        numberCard.sendKeys(Keys.CONTROL + "A");
        numberCard.sendKeys(Keys.DELETE);
        month.sendKeys(Keys.CONTROL + "A");
        month.sendKeys(Keys.DELETE);
        year.sendKeys(Keys.CONTROL + "A");
        year.sendKeys(Keys.DELETE);
        holder.sendKeys(Keys.CONTROL + "A");
        holder.sendKeys(Keys.DELETE);
        code.sendKeys(Keys.CONTROL + "A");
        code.sendKeys(Keys.DELETE);
    }
}
