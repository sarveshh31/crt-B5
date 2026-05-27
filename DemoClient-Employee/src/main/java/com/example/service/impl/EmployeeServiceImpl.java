package com.example.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@Service

public class EmployeeServiceImpl
        implements EmployeeService {

    private final EmployeeRepository repository;


    public EmployeeServiceImpl(EmployeeRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id,
                                   Employee employee) {

        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));

        repository.delete(employee);
    }
}