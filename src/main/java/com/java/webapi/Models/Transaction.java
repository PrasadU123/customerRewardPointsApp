package com.java.webapi.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private long customerId;
    @Column(name = "TRANSACTION_DATE")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Timestamp transactionDate;
    @Column(name = "amount")
    private long amount;


}
