package io.github.wayneh000.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wayneh000.budgetplanner.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
