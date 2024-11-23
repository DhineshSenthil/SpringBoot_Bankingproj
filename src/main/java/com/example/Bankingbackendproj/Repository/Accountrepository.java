package com.example.Bankingbackendproj.Repository;

import com.example.Bankingbackendproj.Model.Accountmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Accountrepository extends JpaRepository<Accountmodel,Integer> {

// CUSTOM QUERY
    @Query("Select a from Accountmodel a where a.balance<500 ")
    List<Accountmodel>checkbalanceminimum();
}
