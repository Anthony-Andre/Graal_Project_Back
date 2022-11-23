package survey.backend.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TraineeDto {
    // private id: number = 0;
    private Integer  id;
    // private lastName: string = ''";
    private String lastName;
    // private firstName: string = "";
    private String firstName;
    // private email: string = "";
    private String email;
    // private phoneNumber: string = "";
    private String phoneNumber;
    // private birthDate!: Date;
    private LocalDate birthDate;
}
