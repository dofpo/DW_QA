package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageCredit {
    public PageCredit() {
        SelenideElement pageCredit = $x("//*[@id='root']/div/button[2]");
        pageCredit.shouldBe(visible);
    }

    private ElementsCollection form = $$(".form-field .input");
    private SelenideElement cardField = form.find(exactText("Номер карты")).$(".input__control");
    private SelenideElement monthField = form.find(exactText("Месяц")).$(".input__control");
    private SelenideElement yearField = form.find(exactText("Год")).$(".input__control");
    private SelenideElement ownerField = form.find(exactText("Владелец")).$(".input__control");
    private SelenideElement codeField = form.find(exactText("CVC/CVV")).$(".input__control");
    private SelenideElement buttonSent = $$("[type='button']").find(exactText("Продолжить"));
    private SelenideElement messageError = $(byText("Ошибка"));
    private SelenideElement messageDecline = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement messageSuccess = $(byText("Успешно"));
    private SelenideElement messageApprove = $(byText("Операция одобрена Банком."));

    public void fillFormCredit(DataHelper.FormFields info) {
        cardField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getHolder());
        codeField.setValue(info.getCode());
    }

    public void toSent() {
        buttonSent.click();
    }

    public void declinedMessage() {
        SelenideElement notificationTitleDenial = $(".notification_status_error").shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
        SelenideElement failureNote = $(".notification_status_error").shouldBe(Condition.text("Ошибка! Банк отказал в проведении операции."), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void approvedMessage() {
        messageSuccess.shouldBe(visible, Duration.ofSeconds(20));
        messageApprove.shouldBe(visible, Duration.ofSeconds(20));
    }
}


