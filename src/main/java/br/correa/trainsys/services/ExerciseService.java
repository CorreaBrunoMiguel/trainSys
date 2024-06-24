package br.correa.trainsys.services;

import br.correa.trainsys.entities.Exercise;
import br.correa.trainsys.repositories.ExerciseRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private final ExerciseRepository repo;

    public ExerciseService(ExerciseRepository repo) {
        this.repo = repo;
    }

    public Exercise createExercise(Exercise exercise) throws BadRequestException {
        if (repo.findByDescription(exercise.getDescription()).isPresent()){
            throw new BadRequestException(
                    "Exercício já cadastrado!"
            );
        }
        return repo.save(exercise);
    }

    public List<Exercise> exerciseList(){
        return repo.findAll();
    }

    public Optional<Exercise> exerciseById(Long id) throws BadRequestException {
        var exercise = repo.findById(id);
        if (exercise.isEmpty()) {
            throw new BadRequestException(
                    String.format(
                            "Exercício com id: %d não encontrado",
                            id
                    )
            );
        } else {
            return exercise;
        }
    }

    public Exercise update(
            Long id,
            Exercise exercise) throws Exception {
        Exercise updatedExercise = repo.getReferenceById(id);

        if (updatedExercise.getId() != null){
            updatedExercise.setDescription(exercise.getDescription());
        } else {
            throw new BadRequestException(
                    "Exercício não encontrado!"
            );
        }

        return repo.save(updatedExercise);
    }

    public void delete(Long id) throws Exception {
        var exerciseToDelete = repo.getReferenceById(id);

        if (!exerciseToDelete.getDescription().isEmpty()){
            repo.delete(exerciseToDelete);
        } else {
            throw new BadRequestException(
                    "Exercicico não encontrado"
            );
        }
    }
}
