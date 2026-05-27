package com.rcoem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rcoem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}