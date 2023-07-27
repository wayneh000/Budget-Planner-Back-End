package wayneh000.budgetplanner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import wayneh000.budgetplanner.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByUsername(String username);
}
