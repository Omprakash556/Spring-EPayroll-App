package com.example.EmployeePayrollApp.controlller;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return service.saveEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployeeDTO) {
        return service.updateEmployee(id, updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}