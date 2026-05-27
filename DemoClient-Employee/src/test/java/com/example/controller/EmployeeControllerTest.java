package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)

class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldSaveEmployee() throws Exception {

        Employee employee = Employee.builder()
                .id(1L)
                .name("Sachin")
                .email("sachin@gmail.com")
                .department("IT")
                .salary(50000.0)
                .build();

        when(service.saveEmployee(employee))
                .thenReturn(employee);

        mockMvc.perform(post("/api/employees")

                        .contentType(MediaType.APPLICATION_JSON)

                        .content(
                                mapper.writeValueAsString(employee)
                        ))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.name")
                        .value("Sachin"));
    }
}