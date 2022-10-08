package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.databaseentities.CreditRequestEntity;
import ru.netology.databaseentities.StatusOperationBuying;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String url = System.getProperty("db.url");
    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, "app", "pass");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @SneakyThrows
    public static StatusOperationBuying getStatusOperationBuying() {
        var runner = new QueryRunner();
        var getStatus = "SELECT status, transaction_id  FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (Connection connection = getConnection()) {
            val transactionId = runner.query(connection, getStatus, new BeanHandler<>(StatusOperationBuying.class));
            return transactionId;
        }
    }

    @SneakyThrows
    public static String getIdOperationBuying() {

        val runner = new QueryRunner();
        val getId = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection connection = getConnection()) {
            val paymentId = runner.query(connection, getId, new ScalarHandler<>());
            return paymentId.toString();
        }
    }

    @SneakyThrows
    public static String getIdOperationCredit() {
        var runner = new QueryRunner();
        var getId = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        try (Connection connection = getConnection()) {
            return runner.query(connection, getId, new ScalarHandler<>());
        }
    }

    @SneakyThrows
    public static CreditRequestEntity getStatusOperationCredit() {

        var runner = new QueryRunner();
        var getStatus = "SELECT status, bank_id  FROM credit_request_entity  ORDER BY created DESC LIMIT 1";

        try (Connection connection = getConnection()) {
            return runner.query(connection, getStatus, new BeanHandler<>(CreditRequestEntity.class));
        }
    }

    @SneakyThrows
    public static void cleanData() {
        val runner = new QueryRunner();
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";
        val creditRequest = "DELETE FROM credit_request_entity";
        try (val connection = getConnection()) {
            runner.update(connection, order);
            runner.update(connection, payment);
            runner.update(connection, creditRequest);
        }
    }
}
