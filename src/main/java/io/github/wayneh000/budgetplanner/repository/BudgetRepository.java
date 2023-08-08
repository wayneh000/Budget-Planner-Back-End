package io.github.wayneh000.budgetplanner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wayneh000.budgetplanner.entity.Account;
import io.github.wayneh000.budgetplanner.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

	List<Budget> findAllByAccount(Account account);
}
