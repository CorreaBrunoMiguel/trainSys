package br.correa.trainsys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import javax.validation.constraints.Size;
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

    @Column(nullable = false)
    private int studentId;

    @Column(nullable = false)
    private int exerciseId;

    @Column(nullable = false)
    private int repetitions;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private int breakTime;

    @Enumerated
    private Days day;

    @Size(max = 255)
    private String observations;

    @Column(nullable = false)
    private int time;

}
