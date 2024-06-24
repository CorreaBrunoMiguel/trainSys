package br.correa.trainsys.services;

import br.correa.trainsys.entities.Tutor;
import br.correa.trainsys.repositories.TutorRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository repo;
    
    public TutorService(TutorRepository repo) {
        this.repo = repo;
    }

    public Tutor createTutor(Tutor tutor) throws BadRequestException {
        if (repo.findByEmail(tutor.getEmail()).isPresent()){
            throw new BadRequestException(
                    "Tutor já cadastrado!"
            );
        }
        return repo.save(tutor);
    }

    public List<Tutor> tutorList(){
        return repo.findAll();
    }

    public Optional<Tutor> tutorById(Long id) throws BadRequestException {
        var tutor = repo.findById(id);
        if (tutor.isEmpty()) {
            throw new BadRequestException(
                    String.format(
                            "Tutor com id: %d não encontrado",
                            id
                    )
            );
        } else {
            return tutor;
        }
    }
}
