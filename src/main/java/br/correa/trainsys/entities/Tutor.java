package br.correa.trainsys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tutors")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    private String name;

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    private String email;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_birth;

    @Column(nullable = false, unique = true, length = 14)
    @Size(max = 14)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @Column(nullable = false)
    @Size(max = 20)
    private String contact;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String neighborhood;

    @Size(max = 30)
    private String number;

    @Size(max = 30)
    private String street;

    @Size(max = 2, message = "Exemplo SC para Santa Catarina")
    private String state;

    @Size(max = 20)
    private String cep;

    @OneToMany(mappedBy = "tutor")
    private List<Student> student;
}
