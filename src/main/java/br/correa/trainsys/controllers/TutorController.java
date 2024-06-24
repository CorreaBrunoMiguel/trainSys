package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.Tutor;
import br.correa.trainsys.entities.Tutor;
import br.correa.trainsys.services.TutorService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tutors")
public class TutorController {

    private final TutorService tutorService;


    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public Tutor post(
            @RequestBody Tutor tutor
    ) throws BadRequestException {
        return tutorService.createTutor(tutor);
    }

    @GetMapping
    public List<Tutor> getAll(){
        return tutorService.tutorList();
    }

    @GetMapping("/{id}")
    public Optional<Tutor> getById(@PathVariable Long id) throws BadRequestException {
        return tutorService.tutorById(id);
    }
}
