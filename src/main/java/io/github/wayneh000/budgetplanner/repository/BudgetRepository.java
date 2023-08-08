package io.github.wayneh000.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wayneh000.budgetplanner.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

}
