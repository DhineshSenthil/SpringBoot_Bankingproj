package com.example.Bankingbackendproj.Repository;

import com.example.Bankingbackendproj.Model.SupportRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRequestRepository extends JpaRepository<SupportRequestModel,Integer> {
}
