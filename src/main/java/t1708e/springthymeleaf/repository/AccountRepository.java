package t1708e.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708e.springthymeleaf.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
