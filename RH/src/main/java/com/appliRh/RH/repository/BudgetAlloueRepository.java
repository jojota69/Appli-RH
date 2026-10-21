package com.appliRh.RH.repository;

import com.appliRh.RH.model.BudgetAlloue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface BudgetAlloueRepository extends JpaRepository<BudgetAlloue, Integer> {

    @Query("SELECT COALESCE(SUM(b.valeur), 0) FROM BudgetAlloue b")
    BigDecimal sumValeur();
}
