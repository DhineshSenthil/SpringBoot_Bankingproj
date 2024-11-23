package com.example.Bankingbackendproj.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity

public class Accountmodel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private String accountHolderName;
    private int accountNumber;
    private BigDecimal balance;
    private Long contactNumber;
private LocalDateTime localDateTime=LocalDateTime.now();

}