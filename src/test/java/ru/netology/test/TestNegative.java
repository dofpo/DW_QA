package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.ByTourPage;
import ru.netology.page.PageBuy;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestNegative {
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

//    @BeforeEach
//    void setUpSutUrl() {
//        open(System.getProperty("sut.url"));
//    }

//    @org.junit.jupiter.api.Test
//    @DisplayName("Should successfully buying travel by valid card")
//    void shouldBuyTravel() {
//        byTourPage.toBuy()
//                .fillBuyForm(DataHelper.getCardApproved());
//        PageBuy pageBuy = new PageBuy();
//        pageBuy.toSent();
//        pageBuy.approvedMessage();
//        var id = DBHelper.getIdOperationBuying();
//        var status = DBHelper.getStatusOperationBuying();
//        assertEquals(id, status.getTransaction_id());
//        assertEquals("APPROVED", status.getStatus());
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Should successfully buying travel with credit by valid card")
//    void shouldBuyTravelOnCredit() {
//        byTourPage.toBuy()
//                .fillBuyForm(DataHelper.getCardApproved());
//        PageCredit pageCredit = new PageCredit();
//        pageCredit.toSent();
//        pageCredit.approvedMessage();
//        var id = DBHelper.getIdOperationCredit();
//        var status = getStatusOperationCredit();
//        assertEquals(id, status.getBank_id());
//        assertEquals("APPROVED", status.getStatus());
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Should be declined operation buying travel by invalid card")
//    void shouldNoBuyTravel() {
//        byTourPage.toBuy()
//                .fillBuyForm(getCardDeclined());
//        PageBuy pageBuy = new PageBuy();
//        pageBuy.toSent();
//        pageBuy.declinedMessage();
//        var id = DBHelper.getIdOperationBuying();
//        var status = DBHelper.getStatusOperationBuying();
//        assertEquals(id, status.getTransaction_id());
//        assertEquals("DECLINED", status.getStatus());
//    }
//
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Should be declined operation buying travel on credit by invalid card")
//    void shouldNoBuyTravelWithCredit() {
//        byTourPage.toCredit()
//                .fillFormCredit(getCardDeclined());
//        PageCredit pageCredit = new PageCredit();
//        pageCredit.toSent();
//        pageCredit.declinedMessage();
//        var id = DBHelper.getIdOperationCredit();
//        var status = getStatusOperationCredit();
//        assertEquals(id, status.getBank_id());
//        assertEquals("DECLINED", status.getStatus());
//    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages card field enter zero")
    void shouldMessageFieldCardWrong() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCardNumberZero());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldCardMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages field 'Holder' enter number")
    void shouldMessageWrongFieldHolder() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getHolderEnterNumber());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldHolderMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages field 'holder' on cyrillic")
    void shouldMessageWrongFieldHolderOnCyrillic() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getHolderCyrillic());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldHolderMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages field 'Holder' only enter first name")
    void shouldMessageFieldHolderFirstName() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getHolderFirstName());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldHolderMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about wrong data in Holder field. Value 'holder' with special characters")
    void shouldMessageWrongFieldHolderSpecChar() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getHolderWithSpecChar());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        Duration.ofSeconds(10);
        pageBuy.wrongFieldHolderMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about wrong data in 'Code' field. Value 'code' - digit ")
    void shouldMessageWrongFieldCode() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCVVWithOneNumber());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldCodeMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages under fields disappear after change values")
    void shouldDisappearMassagesUnderFields() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getFormAllFieldsSymbol());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldCardMessage();
        pageBuy.wrongFieldMonthMessage();
        pageBuy.wrongFieldYearMessage();
        pageBuy.wrongFieldHolderMessage();
        pageBuy.wrongFieldCodeMessage();
        pageBuy.cleanFieldsForm();
        pageBuy.fillBuyForm((DataHelper.getAnyCard()));
        pageBuy.toSent();
        pageBuy.declinedMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about empty field 'number card'")
    void shouldMessageEmptyFieldsCard() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCardNumberEmpty());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.emptyFieldCardMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about empty field 'Month'")
    void shouldMessageEmptyFieldsMonth() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getMonthEmpty());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.emptyFieldMonthMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about empty field 'year'")
    void shouldMessageEmptyFieldsYear() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getYearEmpty());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.emptyFieldYearMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about empty field 'Holder")
    void shouldMessageEmptyFieldsHolder() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getHolderEmpty());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.emptyFieldHolderMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about empty field 'CVC/CVV")
    void shouldMessageEmptyFieldsCode() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCVVEmpty());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.emptyFieldCodeMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages when enter field 'Month' zero and year not equal '22'")
    void shouldMonthEqualZero() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getMonthEqualZero());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        Duration.ofSeconds(10);
        pageBuy.validityMonthMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages enter wrong value field 'Month'")
    void shouldMonthWrong() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getMonthWrong());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.validityMonthMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages until this year field 'Year'")
    void shouldGetUntilThisYear() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getDateUntilThisYear());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.expiredCardMessageMonth();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages when enter field 'Year' zero")
    void shouldYearEqualZero() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getYearEqualZero());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.expiredCardMessageYear();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages after this year field 'Year'")
    void shouldYearAfterThis() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getYearAfterThis());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.validityYearMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about one symbol field 'Card'")
    void shouldCardOneSymbol() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getCardOneSymbol());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldCardMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about one symbol field 'Year'")
    void shouldYearOneSymbol() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getYearOneSymbol());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldYearMessage();
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should be messages about one symbol field 'Month'")
    void shouldMonthOneSymbol() {
        byTourPage.toBuy()
                .fillBuyForm(DataHelper.getMonthOneSymbol());
        PageBuy pageBuy = new PageBuy();
        pageBuy.toSent();
        pageBuy.wrongFieldMonthMessage();
    }
}