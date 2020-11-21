package vananh.example.multitenant.repository;

import vananh.example.multitenant.model.DataSourceConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {
    boolean existsById(Long id);
    Optional<DataSourceConfig> findByName(String name);
}
