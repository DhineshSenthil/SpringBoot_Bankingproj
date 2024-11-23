package com.example.Bankingbackendproj.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class SupportRequestModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@ManyToOne
@JoinColumn(name = "account_id",nullable = false)
private Accountmodel accountmodel;
private String subject;
private String description;
private LocalDateTime requestdate;
private String status;
private LocalDateTime localDateTime=LocalDateTime.now();

}
