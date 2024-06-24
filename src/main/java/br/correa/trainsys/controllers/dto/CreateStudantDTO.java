package br.correa.trainsys.controllers.dto;

import br.correa.trainsys.entities.Tutor;
import br.correa.trainsys.entities.Workout;

import java.time.LocalDate;

public record CreateStudantDTO(
    String name,
    String email,
    LocalDate date_birth,
    String cpf,
    String contact,
    String city,
    String neighborhood,
    String number,
    String street,
    String state,
    String cep,
    Tutor tutor,
    Workout workout
) {
}
