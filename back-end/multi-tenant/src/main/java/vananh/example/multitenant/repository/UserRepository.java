package vananh.example.multitenant.repository;

import vananh.example.multitenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String emai);

    boolean existsById(Long id);
}
