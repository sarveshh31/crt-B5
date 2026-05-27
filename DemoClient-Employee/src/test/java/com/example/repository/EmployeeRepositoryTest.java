package com.example.repository;

import com.example.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest

class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void shouldSaveEmployee() {

        Employee employee = Employee.builder()
                .name("Sachin")
                .email("sachin@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        Employee saved =
                repository.save(employee);

        assertNotNull(saved.getId());
    }
}