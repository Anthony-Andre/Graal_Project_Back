package survey.backend.service;

import survey.backend.dto.QuestionDto;
import survey.backend.dto.SurveyDto;

import java.util.Collection;
import java.util.Optional;

public interface QuestionService {

    Collection<SurveyDto> findAll();

    Optional<SurveyDto> findById(long id);

    Optional<QuestionDto> search(String title);
    SurveyDto add(SurveyDto surveyDto);

    boolean remove(long surveyId);

}
