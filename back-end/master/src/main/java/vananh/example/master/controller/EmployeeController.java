package vananh.example.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vananh.example.master.model.Employee;
import vananh.example.master.service.EmployeeService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<Employee>> findAll(HttpServletRequest request) {
        List<Employee> employeeList = service.findAll();
        return ResponseEntity.ok(employeeList);
    }
}
