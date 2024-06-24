package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.Tutor;
import br.correa.trainsys.services.TutorService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
