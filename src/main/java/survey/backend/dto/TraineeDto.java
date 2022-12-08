package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Trainee;

import javax.validation.constraints.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {

    private Long id;

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp ="^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$" )
    private String phoneNumber;

    @Past
    private Date birthdate;

    public Trainee toTrainee() {
        Trainee trainee = new Trainee();
        trainee.setId(this.id);
        trainee.setLastName(this.lastname);
        trainee.setFirstName(this.firstname);
        trainee.setEmail(this.email);
        trainee.setPhoneNumber(this.phoneNumber);
        trainee.setBirthDate(this.birthdate);

        return trainee;
    }



}
