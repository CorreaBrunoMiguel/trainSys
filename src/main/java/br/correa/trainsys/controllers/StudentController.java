package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.Student;
import br.correa.trainsys.entities.Student;
import br.correa.trainsys.entities.Student;
import br.correa.trainsys.services.StudentService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public List<Student> getAll(){
        return studentService.studentList();
    }

    @GetMapping("/{id}")
    public Optional<Student> getById(@PathVariable Long id) throws BadRequestException {
        return studentService.studentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student
    ) throws Exception {
        Student studentUpdate = studentService.upateStudent(id, student);

        return studentUpdate != null
                ? ResponseEntity.ok(studentUpdate)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delteById(@PathVariable Long id) throws Exception {
        studentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
