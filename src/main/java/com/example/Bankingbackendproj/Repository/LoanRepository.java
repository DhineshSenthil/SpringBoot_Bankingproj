package com.example.Bankingbackendproj.Repository;

import com.example.Bankingbackendproj.Model.LoanModel;
import com.example.Bankingbackendproj.Model.LoanStatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoanRepository extends JpaRepository<LoanModel,Integer> {

    List<LoanModel> findByApplicantNameAndLoanStatusModel(String applicantName,LoanStatusModel loanStatusModel);
}
