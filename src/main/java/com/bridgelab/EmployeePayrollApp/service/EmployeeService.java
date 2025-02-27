package com.bridgelab.EmployeePayrollApp.service;

import com.bridgelab.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelab.EmployeePayrollApp.logging.LoggerService;
import com.bridgelab.EmployeePayrollApp.model.Employee;
import com.bridgelab.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final LoggerService logger;

    @Autowired
    private Environment env;

    public List<Employee> getAllEmployees() {
        String dbUrl = env.getProperty("spring.datasource.url");
        String dbUsername = env.getProperty("spring.datasource.username");
        logger.info("Fetching all employees from DB: {} with user: {}", dbUrl, dbUsername);
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        logger.info("Saving new employee: {}", employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        logger.info("Updating employee with ID: {}", id);
        Optional<Employee> existingEmployee = repository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            logger.info("Before update - Name: {}, Department: {}, Salary: {}", emp.getName(), emp.getDepartment(), emp.getSalary());
            emp.setName(updatedEmployeeDTO.getName());
            emp.setDepartment(updatedEmployeeDTO.getDepartment());
            emp.setSalary(updatedEmployeeDTO.getSalary());
            repository.save(emp);
            logger.info("After update - Name: {}, Department: {}, Salary: {}", emp.getName(), emp.getDepartment(), emp.getSalary());
            logger.info("Employee with ID: {} updated successfully", id);
            return emp;
        }
        logger.error("Employee with ID: {} not found for update", id);
        return null;
    }

    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);
        repository.deleteById(id);
        logger.info("Employee with ID: {} deleted successfully", id);
    }
}