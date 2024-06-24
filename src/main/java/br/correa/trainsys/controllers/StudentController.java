package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.Student;
import br.correa.trainsys.services.StudentService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student post(
            @RequestBody Student student
            ) throws BadRequestException {
        return studentService.createStudent(student);
    }
}
