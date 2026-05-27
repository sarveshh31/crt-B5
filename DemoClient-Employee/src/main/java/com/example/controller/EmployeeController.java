package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")



public class EmployeeController {

    private final EmployeeService service;


    public EmployeeController(EmployeeService service) {
        super();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee>
    saveEmployee(@Valid @RequestBody Employee employee) {

        return ResponseEntity.ok(
                service.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>>
    getAllEmployees() {

        return ResponseEntity.ok(
                service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee>
    getEmployeeById(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee>
    updateEmployee(@PathVariable Long id,
                   @RequestBody Employee employee) {

        return ResponseEntity.ok(
                service.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteEmployee(@PathVariable Long id) {

        service.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted");
    }
}