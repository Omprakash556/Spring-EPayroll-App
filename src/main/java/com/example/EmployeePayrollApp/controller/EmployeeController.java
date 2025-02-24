
package com.example.EmployeePayrollApp.controller;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("Received request to fetch all employees");
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Received request to fetch employee with ID: {}", id);
        return service.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Received request to create employee: {}", employeeDTO);
        return service.saveEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployeeDTO) {
        log.info("Received request to update employee with ID: {}", id);
        return service.updateEmployee(id, updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        log.info("Received request to delete employee with ID: {}", id);
        service.deleteEmployee(id);
    }
}
