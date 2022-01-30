package pl.unlimited.windows.serwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.unlimited.windows.serwer.model.SystemUser;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {
    SystemUser findSystemUserByLogin(String login);
}
