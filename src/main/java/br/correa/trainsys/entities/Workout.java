package br.correa.trainsys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Student student;

    @OneToMany
    private List<Exercise> segunda;

    @OneToMany
    private List<Exercise> terça;

    @OneToMany
    private List<Exercise> quarta;

    @OneToMany
    private List<Exercise> quinta;

    @OneToMany
    private List<Exercise> sexta;

}
