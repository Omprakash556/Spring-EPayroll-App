package com.bridgelab.EmployeePayrollApp.service;

import com.bridgelab.EmployeePayrollApp.dto.EmployeeDTO;
import com.bridgelab.EmployeePayrollApp.logging.LoggerService;
import com.bridgelab.EmployeePayrollApp.model.Employee;
import com.bridgelab.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class EmployeeService {
    private final EmployeeRepository repository;
    private final LoggerService logger;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees in profile: {}", activeProfile);
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee with ID: {} in profile: {}", id, activeProfile);
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        logger.info("Saving new employee: {} in profile: {}", employeeDTO, activeProfile);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        logger.info("Updating employee with ID: {} in profile: {}", id, activeProfile);
        Optional<Employee> existingEmployee = repository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(updatedEmployeeDTO.getName());
            emp.setDepartment(updatedEmployeeDTO.getDepartment());
            emp.setSalary(updatedEmployeeDTO.getSalary());
            return repository.save(emp);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {} in profile: {}", id, activeProfile);
        repository.deleteById(id);
    }
}