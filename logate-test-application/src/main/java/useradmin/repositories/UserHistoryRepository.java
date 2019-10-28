package useradmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import useradmin.domain.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
