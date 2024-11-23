package com.example.Bankingbackendproj.Controller;


import com.example.Bankingbackendproj.Model.Accountmodel;
import com.example.Bankingbackendproj.Model.LoanModel;
import com.example.Bankingbackendproj.Model.LoanStatusModel;
import com.example.Bankingbackendproj.Service.Accountservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Account")
public class Accountcontroller {
    @Autowired      //access controller to the service class
    Accountservice accountservice;

    //Return type is based on what we want(Type -accountmodel,listofaccountmodel,string,int and so on)

    @PostMapping("/addAccount")
    public Accountmodel createAccount(@RequestBody Accountmodel accountmodel) {
        return accountservice.create(accountmodel);
    }

    @GetMapping("/addAccount")
    public String createaccount(@RequestBody Accountmodel accountmodel) {
        accountservice.gettingaccount(accountmodel);
        return accountservice.gettingaccount(accountmodel);
    }

    @PostMapping("/addAllAccount")
    public List<Accountmodel> createAll(@RequestBody List<Accountmodel> accountmodel) {
        return accountservice.gettingAllaccount(accountmodel);
    }

    @GetMapping("/getAllAccount")
    public List<Accountmodel>getAllaccount(){
        return accountservice.getAllaccount();
    }
    @GetMapping("/getAccountdetails/{id}")
    public Accountmodel getaccountdetails(@PathVariable int id) {
        return accountservice.getaccountdetails(id);
    }
@DeleteMapping("/deleteAccount/{id}")
    public String deleteaccountbyid(@PathVariable int id){
        return accountservice.deleteaccountbyid(id);
}
@PutMapping("updateAccount/{id}")
    public Accountmodel updateAccountDetails(@RequestBody Accountmodel accountmodel,@PathVariable int id){
        return accountservice.updateAccountDetails(accountmodel,id);
}
@GetMapping("getLessthanMinimumBalance")
    public List<Accountmodel>checkbalanceminimum(){
        return accountservice.checkbalanceminimumlessthan500();
}
@PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id, @RequestParam("amount")BigDecimal amount){
        return accountservice.withdrawAmount(id,amount);
}
@PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id,@RequestParam("amount") BigDecimal amount){
        return accountservice.depositAmount(id,amount);
    }
    @GetMapping("/balanceChecking/{id}")
    public BigDecimal getbalance(@PathVariable int id){
        return accountservice.getbalanceAmount(id);
    }
    //apply for loan
    @PutMapping("/apply")
    public LoanModel applyForLoan(@RequestBody LoanModel loanModel){
        return accountservice.applyForLoan(loanModel);
    }

    // Approve a loan
    @PutMapping("/approve/{loanId}")
    public LoanModel approveLoan(@PathVariable int loanId) {
        return accountservice.approveLoan(loanId);
    }
    // Reject a loan
    @PutMapping("/reject/{loanId}")
    public LoanModel rejectLoan(@PathVariable int loanId) {
        return accountservice.rejectLoan(loanId);
    }

    // Get loan by ID
    @GetMapping("/{loanId}")
    public LoanModel getLoanById(@PathVariable int loanId) {
        return accountservice.getLoanById(loanId);
    }

    // Get all loans
    @GetMapping("/all")
    public List<LoanModel> getAllLoans() {
        return accountservice.getAllLoans();
    }

    // Get all approved loans
    @GetMapping("/approvedLoans")
    public List<LoanModel> getApprovedLoans(@RequestParam String applicantName,@RequestBody LoanStatusModel loanStatusModel) {

        return accountservice.getApprovedLoans(applicantName,loanStatusModel);
    }
@PostMapping("/payBill/{accountId}")
public String payBill(@PathVariable int accountId,@RequestParam String billerName,@RequestParam BigDecimal amount){
        return accountservice.payBill(accountId,billerName,amount);
}
    @PostMapping("/createRequest/{accountId}")
    public String createRequest(@PathVariable int accountId,
                                @RequestParam String subject,
                                @RequestParam String description) {
        return accountservice.createSupportRequest(accountId, subject, description);
    }

    @PutMapping("/updateStatus/{requestId}")
    public String updateStatus(@PathVariable int requestId, @RequestParam String status) {
        return accountservice.updateRequestStatus(requestId, status);
    }
}

