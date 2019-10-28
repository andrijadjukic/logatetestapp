package useradmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import useradmin.domain.RoleUserRef;

import java.util.Optional;

public interface RoleUserRefRepository extends JpaRepository<RoleUserRef, Long> {
    @Query("select ref " +
            "from RoleUserRef ref " +
            "where ref.userid=?1 " +
            "and ref.roleid=?2")
    Optional<RoleUserRef> getRoleUserRefByUserIdAndRoleID(Long userId, Long roleId);
}
