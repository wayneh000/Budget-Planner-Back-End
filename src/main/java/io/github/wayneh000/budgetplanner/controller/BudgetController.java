package io.github.wayneh000.budgetplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.wayneh000.budgetplanner.service.BudgetService;

@RestController
@RequestMapping("api/v1/budget")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;
}
