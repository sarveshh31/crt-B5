package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee;

    @BeforeEach
    void setup() {

        employee = Employee.builder()
                .id(1L)
                .name("Sachin")
                .email("sachin@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();
    }

    @Test
    void shouldSaveEmployee() {

        when(repository.save(employee))
                .thenReturn(employee);

        Employee saved =
                service.saveEmployee(employee);

        assertNotNull(saved);

        assertEquals("Sachin",
                saved.getName());
    }

    @Test
    void shouldGetEmployeeById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee found =
                service.getEmployeeById(1L);

        assertEquals(1L,
                found.getId());
    }

    @Test
    void shouldDeleteEmployee() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        service.deleteEmployee(1L);

        verify(repository,
                times(1))
                .delete(employee);
    }
}