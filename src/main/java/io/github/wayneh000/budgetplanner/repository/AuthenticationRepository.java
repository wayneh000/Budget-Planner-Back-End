package io.github.wayneh000.budgetplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.wayneh000.budgetplanner.entity.Account;

@Repository
public interface AuthenticationRepository extends JpaRepository<Account, Integer> {

}
