package com.example.Bankingbackendproj.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class BillPaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Accountmodel accountmodel;

    private String billerName;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String paymentStatus; // e.g., "Pending", "Completed"
    private LocalDateTime localDateTime=LocalDateTime.now();
}
