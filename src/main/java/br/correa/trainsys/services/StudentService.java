package br.correa.trainsys.services;

import br.correa.trainsys.entities.Student;
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

    public List<Student> studentList(){
        return repo.findAll();
    }

    public Optional<Student> studentById(Long id) throws BadRequestException {
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

    public Student upateStudent(Long id, Student student) throws BadRequestException {
        var studantToUpdate = repo.getReferenceById(id);
        if (studantToUpdate.getId() != null){
            if (student.getName() != null)
                studantToUpdate.setName(student.getName());
            if (student.getEmail() != null)
                studantToUpdate.setEmail(student.getEmail());
            if (student.getDate_birth() != null)
                studantToUpdate.setDate_birth(student.getDate_birth());
            if (student.getCpf() != null)
                studantToUpdate.setCpf(student.getCpf());
            if (student.getContact() != null)
                studantToUpdate.setContact(student.getContact());
            if (student.getCity() != null)
                studantToUpdate.setCity(student.getCity());
            if (student.getNeighborhood() != null)
                studantToUpdate.setNeighborhood(student.getNeighborhood());
            if (student.getStreet() != null)
                studantToUpdate.setStreet(student.getStreet());
            if (student.getState() != null)
                studantToUpdate.setState(student.getState());
            if (student.getCep() != null)
                studantToUpdate.setCep(student.getCep());

            return repo.save(studantToUpdate);
        } else {
            throw new BadRequestException(
                    "Estudante não encontrado."
            );
        }


    }

    public void delete(Long id) throws Exception {
        var StudentToDelete = repo.getReferenceById(id);

        if (!StudentToDelete.getEmail().isEmpty()){
            repo.delete(StudentToDelete);
        } else {
            throw new BadRequestException(
                    "Exercicico não encontrado"
            );
        }
    }


}
