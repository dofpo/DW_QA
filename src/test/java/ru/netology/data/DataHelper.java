package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class DataHelper {
    private DataHelper() {
    }

    public static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class FormFields {

        private String number;
        private String month;
        private String year;
        private String holder;
        private String code;

    }

    public static String getNumberCard() {
        return faker.business().creditCardNumber();
    }

    public static DateMonthYear getDate() {
        var date = faker.date().future(365 * 5, TimeUnit.DAYS);
        var month = new DecimalFormat("00").format(date.getMonth() + 1);
        var year = String.valueOf(date.getYear() - 100);
        return new DateMonthYear(month, year);
    }

    public static String getHolder() {
        return faker.name().fullName();
    }

    public static String getCode() {
        return faker.number().digits(3);
    }

    public static FormFields getCardApproved() {
        var number = "4444 4444 4444 4441";
        var date = getDate();
        return new FormFields(number, date.getMonth(), date.getYear(), getHolder(), getCode());
    }

    public static FormFields getCardDeclined() {
        var number = "4444 4444 4444 4442";
        var date = getDate();
        return new FormFields(number, date.getMonth(), date.getYear(), getHolder(), getCode());
    }

    @Value
    private static class DateMonthYear {
        String month;
        String year;
    }

    public static FormFields getCardNumberZero() {
        var number = "0";
        var date = getDate();
        return new FormFields(number, date.getMonth(), date.getYear(), getHolder(), getCode());
    }

    public static FormFields getAnyCard() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.getYear(), getHolder(), getCode());
    }

    public static FormFields getMonthEqualZero() {
        var date = getDate();
        return new FormFields(getNumberCard(), "00", date.getYear(), getHolder(), getCode());
    }

    public static FormFields getMonthWrong() {
        var date = getDate();
        return new FormFields(getNumberCard(), "13", date.getYear(), getHolder(), getCode());
    }

    public static String getSymbol() {
        return String.valueOf(new Random().nextInt(10));
    }

    public static FormFields getMonthOneSymbol() {
        var date = getDate();
        return new FormFields(getNumberCard(), getSymbol(), date.getYear(), getHolder(), getCode());
    }

    public static FormFields getMonthEmpty() {
        var date = getDate();
        return new FormFields(getNumberCard(), "", date.getYear(), getHolder(), getCode());
    }

    public static FormFields getYearOneSymbol() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), getSymbol(), getHolder(), getCode());
    }

    public static FormFields getCardOneSymbol() {
        var date = getDate();
        return new FormFields(getSymbol(), date.getMonth(), date.year, getHolder(), getCode());
    }

    public static FormFields getYearEmpty() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), "", getHolder(), getCode());
    }

    public static FormFields getYearAfterThis() {
        var date = getDate();
        var dateWrong = faker.date().future(365 * 10, 365 * 5, TimeUnit.DAYS);
        var year = String.valueOf(dateWrong.getYear() - 100);
        return new FormFields(getNumberCard(), date.getMonth(), year, getHolder(), getCode());
    }

    public static FormFields getYearEqualZero() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), "00", getHolder(), getCode());
    }

    public static FormFields getDateUntilThisYear() {
        LocalDate date = LocalDate.now();
        var year = new DecimalFormat("00").format(date.getYear() - 2000);
        int month = date.getMonthValue();
        var valueMonth = String.valueOf(new DecimalFormat("00").format(new Random().nextInt(month)));
        return new FormFields(getNumberCard(), valueMonth, year, getHolder(), getCode());
    }

    public static FormFields getHolderEnterNumber() {
        var date = getDate();
        var holder = faker.number().digits(3);
        return new FormFields(getNumberCard(), date.getMonth(), date.year, holder, getCode());
    }

    public static FormFields getHolderCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        var holder = faker.name().fullName();
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, holder, getCode());
    }

    public static FormFields getHolderFirstName() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, faker.name().firstName(), getCode());
    }

    public static FormFields getHolderWithSpecChar() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, getHolder() + "#", getCode());
    }

    public static FormFields getCVVWithOneNumber() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, getHolder(), getSymbol());
    }

    public static FormFields getFormAllFieldsSymbol() {
        return new FormFields(getSymbol(), getSymbol(), getSymbol(), getSymbol(), getSymbol());
    }

    public static FormFields getCardNumberEmpty() {
        var date = getDate();
        return new FormFields("", date.getMonth(), date.year, getHolder(), getCode());
    }

    public static FormFields getCVVEmpty() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, getHolder(), "");
    }

    public static FormFields getHolderEmpty() {
        var date = getDate();
        return new FormFields(getNumberCard(), date.getMonth(), date.year, "", getCode());
    }
}


