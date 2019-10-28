package useradmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import useradmin.domain.RoleHistory;

public interface RoleHistoryRepository extends JpaRepository<RoleHistory, Long> {
}
