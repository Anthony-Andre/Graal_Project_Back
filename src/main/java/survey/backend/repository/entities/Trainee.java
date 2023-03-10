package survey.backend.repository.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trainee")
@Getter @Setter
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="birth_date", nullable = true)
    private Date birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


}
