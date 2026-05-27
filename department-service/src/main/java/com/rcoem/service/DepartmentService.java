package com.rcoem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcoem.entity.Department;
import com.rcoem.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Department updateDepartment(Long id, Department department) {

        Department existing = repository.findById(id).orElse(null);

        existing.setDeptName(department.getDeptName());
        existing.setHead(department.getHead());

        return repository.save(existing);
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}