package vananh.example.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vananh.example.master.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
