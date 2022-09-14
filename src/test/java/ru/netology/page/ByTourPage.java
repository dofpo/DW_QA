package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ByTourPage {

    public PageBuy toBuy() {
        SelenideElement buttonBuy = $x("//*[@id='root']/div/button[1]");
        buttonBuy.click();
        return new PageBuy();
    }

    public PageCredit toCredit() {
        SelenideElement buttonCredit = $x("//*[text()='Купить в кредит']");
        buttonCredit.click();
        return new PageCredit();
    }

    public void pageCredit() {
        SelenideElement pageCredit = $x("//*[text()='Кредит по данным карты']");
        pageCredit.shouldBe(visible);
    }


}
