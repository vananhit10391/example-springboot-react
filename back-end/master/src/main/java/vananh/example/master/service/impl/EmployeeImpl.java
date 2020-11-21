package vananh.example.master.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vananh.example.master.model.Employee;
import vananh.example.master.repository.EmployeeRepository;
import vananh.example.master.service.EmployeeService;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }
}
