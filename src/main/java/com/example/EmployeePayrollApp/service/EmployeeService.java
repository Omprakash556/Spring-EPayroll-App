package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.dto.EmployeeDTO;
import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        log.info("Saving new employee: {}", employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        log.info("Updating employee with ID: {}", id);
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
        log.info("Deleting employee with ID: {}", id);
        repository.deleteById(id);
    }
}
