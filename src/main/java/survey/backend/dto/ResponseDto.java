package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import survey.backend.repository.entities.Question;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private int id;
    private String answer;
    private Question question;

}
