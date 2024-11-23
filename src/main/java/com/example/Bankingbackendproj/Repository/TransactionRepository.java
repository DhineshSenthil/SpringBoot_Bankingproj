package com.example.Bankingbackendproj.Repository;

import com.example.Bankingbackendproj.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {
}
