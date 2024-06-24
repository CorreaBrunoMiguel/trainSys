package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.Exercise;
import br.correa.trainsys.entities.User;
import br.correa.trainsys.services.ExerciseService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise) throws BadRequestException {
        return exerciseService.createExercise(exercise);
    }

    @GetMapping
    public List<Exercise> exerciseList(){
        return exerciseService.exerciseList();
    }

    @GetMapping("/{id}")
    public Optional<Exercise> getById(@PathVariable Long id) throws BadRequestException {
        return exerciseService.exerciseById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(
            @PathVariable Long id,
            @RequestBody Exercise exercise
    ) throws Exception {
        Exercise exerciseUpdate = exerciseService.update(id, exercise);

        return exerciseUpdate != null
                ? ResponseEntity.ok(exerciseUpdate)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExercise(@PathVariable Long id) throws Exception {
        exerciseService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
