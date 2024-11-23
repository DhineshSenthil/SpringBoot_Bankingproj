package com.example.Bankingbackendproj.Repository;

import com.example.Bankingbackendproj.Model.BillPaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPayementRepository extends JpaRepository<BillPaymentModel,Integer> {
}
