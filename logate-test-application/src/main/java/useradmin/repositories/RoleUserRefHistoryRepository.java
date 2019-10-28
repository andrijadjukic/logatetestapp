package useradmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import useradmin.domain.RoleUserRefHistory;

public interface RoleUserRefHistoryRepository extends JpaRepository<RoleUserRefHistory, Long> {
}
