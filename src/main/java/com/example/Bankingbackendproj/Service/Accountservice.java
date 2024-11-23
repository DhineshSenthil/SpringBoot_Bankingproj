package com.example.Bankingbackendproj.Service;

import com.example.Bankingbackendproj.Model.*;
import com.example.Bankingbackendproj.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Accountservice {
    @Autowired
    Accountrepository accountrepository;
@Autowired
    TransactionRepository transactionRepository;
@Autowired
    LoanRepository loanRepository;
@Autowired
BillPayementRepository billPaymentRepository;
@Autowired
    SupportRequestRepository supportRequestRepository;
    public Accountmodel create(Accountmodel accountmodel) {
        return accountrepository.save(accountmodel);

    }

    public String gettingaccount(Accountmodel accountmodel) {
        accountrepository.save(accountmodel);
        return "The Account " + accountmodel.getId() + "created";
    }

    public List<Accountmodel> gettingAllaccount(List<Accountmodel> accountmodel) {
        return accountrepository.saveAll(accountmodel);

    }

    public Accountmodel getaccountdetails(int id) {
        return accountrepository.findById(id).orElse(null);
    }

    public String deleteaccountbyid(int id) {
        accountrepository.deleteById(id);
        return "The Account deleted";
    }

    public List<Accountmodel> getAllaccount() {
        return accountrepository.findAll();
    }
//update
    public Accountmodel updateAccountDetails(Accountmodel accountmodel, int id) {
        Accountmodel oldData = null;
        oldData = accountrepository.findById(id).orElse(null);
        oldData.setAccountHolderName(accountmodel.getAccountHolderName());
        oldData.setBalance(accountmodel.getBalance());
        oldData.setAccountNumber(accountmodel.getAccountNumber());
        oldData.setContactNumber(accountmodel.getContactNumber());
        return accountrepository.save(oldData);

    }

    public List<Accountmodel> checkbalanceminimumlessthan500() {
        return accountrepository.checkbalanceminimum();
    }
//Withdraw
    public String withdrawAmount(int id, BigDecimal amount) {
        Accountmodel accountData = accountrepository.findById(id).orElse(null);
        if (accountData == null) {
            return "Account not found";
        }
        //Logic-balance >amount and add it
        if (accountData.getBalance().compareTo(amount) < 0) {
            return "Low balance!!!";
        }
        accountData.setBalance(accountData.getBalance().subtract(amount));
        accountrepository.save(accountData);

        //Transactionprocess
        TransactionModel transactionModel=new TransactionModel();
        transactionModel.setAmount(amount);
        transactionModel.setAccountmodel(accountData);
        transactionModel.setTransactionType("Withdraw Done....");
        transactionModel.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transactionModel);



        return "Withdraw Done";
    }
//Deposit
    public String depositAmount(int id, BigDecimal amount) {
        Accountmodel accountData = accountrepository.findById(id).orElse(null);
        if (accountData == null) {
            return "Account not found";
        }
accountData.setBalance(accountData.getBalance().add(amount));
        accountrepository.save(accountData);

        //Transactionprocess
        TransactionModel transactionModel=new TransactionModel();
        transactionModel.setAmount(amount);
        transactionModel.setAccountmodel(accountData);
        transactionModel.setTransactionType("Deposited");
        transactionModel.setTransactionTime(LocalDateTime.now());
transactionRepository.save(transactionModel);


        return "Deposit Done...";
    }
    //gettingbalance
    public BigDecimal getbalanceAmount(int id){
        Accountmodel accountData = accountrepository.findById(id).orElse(null);
        return accountData.getBalance();
    }




    //LoanProcess
    public LoanModel applyForLoan(LoanModel loanModel)
    {

        if (loanModel.getAccountmodel() == null) {
            throw new IllegalArgumentException("Account not found");
        }

        loanModel.setLoanStatusModel(LoanStatusModel.APPLIED);

        return loanRepository.save(loanModel);
    }
    public LoanModel approveLoan(int loanId){
        Optional<LoanModel> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            LoanModel loan = loanOptional.get();
            loan.setLoanStatusModel(LoanStatusModel.APPROVED);
            loan.setApplicationDate(LocalDateTime.now());
            return loanRepository.save(loan);
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }

    // Reject a loan
    public LoanModel rejectLoan(int loanId) {
        Optional<LoanModel> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            LoanModel loan = loanOptional.get();
            loan.setLoanStatusModel(LoanStatusModel.REJECTED);
            return loanRepository.save(loan);
        }
        throw new RuntimeException("Loan not found with id: " + loanId);
    }

    // Get a loan by ID
    public LoanModel getLoanById(int loanId) {
        return loanRepository.findById(loanId).orElse(null);
    }

    // Get all loans
    public List<LoanModel> getAllLoans() {
        return loanRepository.findAll();
    }

    // Get all approved loans
    public List<LoanModel> getApprovedLoans(String applicantName,LoanStatusModel loanStatusModel) {

        return loanRepository.findByApplicantNameAndLoanStatusModel(applicantName,loanStatusModel);
    }
    public String payBill(int accountId, String billerName, BigDecimal amount) {
        Accountmodel account = accountrepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account not found!";
        }

        if (account.getBalance().compareTo(amount) < 0) {
            return "Insufficient balance!!!";
        }

        BillPaymentModel billPayment = new BillPaymentModel();
        billPayment.setAccountmodel(account);
        billPayment.setBillerName(billerName);
        billPayment.setAmount(amount);
        billPayment.setPaymentDate(LocalDateTime.now());
        billPayment.setPaymentStatus("Completed");

        account.setBalance(account.getBalance().subtract(amount));
        accountrepository.save(account);

        billPaymentRepository.save(billPayment);

        return "Bill payment successful!";
    }
    public String createSupportRequest(int accountId, String subject, String description) {
        Accountmodel account = accountrepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account not found!";
        }

        SupportRequestModel request = new SupportRequestModel();
        request.setAccountmodel(account);
        request.setSubject(subject);
        request.setDescription(description);
        request.setStatus("Open");

        supportRequestRepository.save(request);

        return "Support request created successfully!";
    }

    public String updateRequestStatus(int requestId, String status) {
        SupportRequestModel request = supportRequestRepository.findById(requestId).orElse(null);
        if (request == null) {
            return "Request not found!";
        }

        request.setStatus(status);
        supportRequestRepository.save(request);

        return "Support request status updated successfully!";
    }

}

