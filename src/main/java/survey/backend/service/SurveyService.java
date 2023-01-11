package survey.backend.service;

import survey.backend.dto.PoeFullDto;
import survey.backend.dto.SurveyDto;
import survey.backend.dto.TraineeDto;

import java.util.Collection;
import java.util.Optional;

public interface SurveyService {

    Collection<SurveyDto> findAll();

    Optional<SurveyDto> findById(long id);

    SurveyDto add(SurveyDto surveyDto);

    Optional<SurveyDto> addQuestion(long surveyId, long questionId);

    Optional<SurveyDto> addQuestions(long surveyId, Collection<Long> questionsIds);

    boolean remove(long surveyId);
}
