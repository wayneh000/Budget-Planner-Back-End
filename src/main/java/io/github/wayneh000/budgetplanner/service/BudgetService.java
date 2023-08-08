package io.github.wayneh000.budgetplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.wayneh000.budgetplanner.repository.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;
}
