package com.example.EmployeePayrollApp.service;

import com.example.EmployeePayrollApp.model.Employee;
import com.example.EmployeePayrollApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = repository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            emp.setName(updatedEmployee.getName());
            emp.setSalary(updatedEmployee.getSalary());
            emp.setDepartment(updatedEmployee.getDepartment());
            return repository.save(emp);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}