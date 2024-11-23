package com.example.Bankingbackendproj.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class TransactionModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne
    //for joining from account and tarnsaction
    @JoinColumn(name = "account_id",nullable = false)
    private Accountmodel accountmodel;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
    private String transactionType;
}

