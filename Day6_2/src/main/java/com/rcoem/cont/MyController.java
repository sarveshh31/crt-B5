package com.rcoem.cont;

import com.rcoem.Entity.Student;
import com.rcoem.repo.StudentRepository;
import com.rcoem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/getStudent")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public Boolean deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}