package survey.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import survey.backend.repository.entities.Question;
import survey.backend.dto.enums.Level;
import survey.backend.dto.enums.PoeType;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SurveyDto {

    private Long id;
    private String title;
    private Level level;
    private PoeType poeType;
    private List<Question> questions;

}
