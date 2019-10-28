package useradmin.repositories;

import org.springframework.data.jpa.repository.Query;
import useradmin.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r " +
            "from Role r " +
            "Inner join RoleUserRef ref " +
            "on r.id = ref.roleid " +
            "where ref.userid=?1 " +
            "and ref.IsRemoved = 0")
    Set<Role> getAllUserRoles(Long id);
}
