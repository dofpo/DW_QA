package ru.netology.databaseentities;

import lombok.Data;

@Data
public class CreditRequestEntity {
    String id;
    String bank_id;
    String created;
    String status;
}

