
package com.example.Bankingbackendproj.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class LoanModel {


@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String applicantName;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Accountmodel accountmodel;

    // Fixed the field name to match the repository query method

    @Enumerated(EnumType.STRING)
    private LoanStatusModel loanStatusModel;

    private BigDecimal loanAmounts;
    private BigDecimal interestRate;
    private int loanTermMonth;
    private LocalDateTime applicationDate;

}
