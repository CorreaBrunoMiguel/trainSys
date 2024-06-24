package br.correa.trainsys.services;

import br.correa.trainsys.entities.Student;
import br.correa.trainsys.repositories.StudentRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student createStudent(Student student) throws BadRequestException {
        if (repo.findByEmail(student.getEmail()).isPresent()){
            throw new BadRequestException(
                    "Aluno já cadastrado!"
            );
        }
        return repo.save(student);
    }

    public List<Student> userList(){
        return repo.findAll();
    }

    public Optional<Student> userById(Long id) throws BadRequestException {
        var student = repo.findById(id);
        if (student.isEmpty()) {
            throw new BadRequestException(
                    String.format(
                            "Aluno com id: %d não encontrado",
                            id
                    )
            );
        } else {
            return student;
        }
    }


}