package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.ByTourPage;
import ru.netology.page.PageBuy;
import ru.netology.page.PageCredit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBHelper.getStatusOperationCredit;
import static ru.netology.data.DataHelper.getCardDeclined;

public class TestPositive {
    public ByTourPage byTourPage = new ByTourPage();

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");
    }
    void setUpSutUrl() {
        open(System.getProperty("sut.url"));
    }
    @AfterAll
    static void tearDownAll() {
        DBHelper.cleanData();
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should successfully buying travel by valid card")
    void shouldBuyTravel() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCardApproved());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.approvedMessage();
        var id = DBHelper.getIdOperationBuying();
        var status = DBHelper.getStatusOperationBuying();
        assertEquals(id, status.getTransaction_id());
        assertEquals("APPROVED", status.getStatus());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should successfully buying travel with credit by valid card")
    void shouldBuyTravelOnCredit() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCardApproved());
        PageCredit pageCredit = new PageCredit();
        pageCredit.toSent();
        pageCredit.approvedMessage();
        var id = DBHelper.getIdOperationCredit();
        var status = getStatusOperationCredit();
        assertEquals(id, status.getBank_id());
        assertEquals("APPROVED", status.getStatus());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be declined operation buying travel by invalid card")
    void shouldNoBuyTravel() {
        byTourPage.toBuy()
                .fillBuyForm(getCardDeclined());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.declinedMessage();
        var id = DBHelper.getIdOperationBuying();
        var status = DBHelper.getStatusOperationBuying();
        assertEquals(id, status.getTransaction_id());
        assertEquals("DECLINED", status.getStatus());
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Should be declined operation buying travel on credit by invalid card")
    void shouldNoBuyTravelWithCredit() {
        byTourPage.toCredit()
                .fillFormCredit(getCardDeclined());
        PageCredit pageCredit = new PageCredit();
        pageCredit.toSent();
        pageCredit.declinedMessage();
        var id = DBHelper.getIdOperationCredit();
        var status = getStatusOperationCredit();
        assertEquals(id, status.getBank_id());
        assertEquals("DECLINED", status.getStatus());
    }
}
