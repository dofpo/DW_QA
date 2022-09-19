package ru.netology.test;

import lombok.val;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.DataHelper.getCardApproved;
import static ru.netology.data.DataHelper.getCardDeclined;
import static ru.netology.data.RestHelper.*;

public class TestApi {
    @org.junit.jupiter.api.Test
    public void shouldGetStatusValidApprovedCardPayment() {
        val validApprovedCard = getCardApproved();
        val status = sentFormBuy(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @org.junit.jupiter.api.Test
    void shouldGetStatusValidApprovedCardCreditRequest() {
        val validApprovedCard = getCardApproved();
        val status = sentFormCredit(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @org.junit.jupiter.api.Test
    void shouldGetStatusValidDeclinedCardPayment() {
        val validDeclinedCard = getCardDeclined();
        val status = sentFormBuy(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @org.junit.jupiter.api.Test
    void shouldGetStatusValidDeclinedCardCreditRequest() {
        val validDeclinedCard = getCardDeclined();
        val status = sentFormCredit(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }
}
