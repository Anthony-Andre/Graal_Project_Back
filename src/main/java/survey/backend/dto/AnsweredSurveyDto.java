package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import survey.backend.repository.entities.Response;
import survey.backend.repository.entities.Survey;
import survey.backend.repository.entities.Trainee;

import java.util.List;

@SuperBuilder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnsweredSurveyDto {

    private int Id;
    private Survey survey;
    private Trainee trainee;
    private List<Response> responses;

}
