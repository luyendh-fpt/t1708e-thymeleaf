package t1708e.springthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t1708e.springthymeleaf.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
