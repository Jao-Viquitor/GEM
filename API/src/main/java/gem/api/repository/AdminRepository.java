package gem.api.repository;

import gem.api.model.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT u FROM Admin u WHERE u.login = :login")
    UserDetails findByLogin(@Param("login") String login);

}
