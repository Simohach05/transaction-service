package com.greenwealth.transaction_service.domain.repository;

import com.greenwealth.transaction_service.domain.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
