package com.example.crud.service;

import com.example.crud.model.Employee;
import com.example.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    // READ
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Optional<Employee> updateEmployee(Long id, Employee updated) {
        return repository.findById(id).map(emp -> {
            emp.setName(updated.getName());
            emp.setEmail(updated.getEmail());
            emp.setDepartment(updated.getDepartment());
            return repository.save(emp);
        });
    }

    // DELETE
    public boolean deleteEmployee(Long id) {
        return repository.findById(id).map(emp -> {
            repository.delete(emp);
            return true;
        }).orElse(false);
    }
}
