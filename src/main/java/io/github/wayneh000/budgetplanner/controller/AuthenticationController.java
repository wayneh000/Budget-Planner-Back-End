package io.github.wayneh000.budgetplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import io.github.wayneh000.budgetplanner.service.AuthenticationService;

@RestController("/api/v1/auth/")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
}
