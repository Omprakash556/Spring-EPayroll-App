
package com.bridgelab.EmployeePayrollApp.controller;

//import com.bridgelab.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelab.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelab.EmployeePayrollApp.logging.LoggerService;
import com.bridgelab.EmployeePayrollApp.model.Employee;
import com.bridgelab.EmployeePayrollApp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    private final LoggerService logger;

    @GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("Received request to fetch all employees");
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Received request to fetch employee with ID: {}", id);
        return service.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Received request to create employee: {}", employeeDTO);
        return service.saveEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployeeDTO) {
        logger.info("Received request to update employee with ID: {}", id);
        return service.updateEmployee(id, updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Received request to delete employee with ID: {}", id);
        service.deleteEmployee(id);
    }
}
