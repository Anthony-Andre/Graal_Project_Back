package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import survey.backend.dto.enums.AnswerType;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String text;
    private AnswerType answerType;
    private List<String> answersProposed = new ArrayList<>();
}
