package br.correa.trainsys.repositories;

import br.correa.trainsys.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByDescription(@Size(max = 255) String description);
}
