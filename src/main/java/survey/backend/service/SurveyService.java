package survey.backend.service;

import survey.backend.dto.SurveyDto;

import java.util.Collection;
import java.util.Optional;

public interface SurveyService {

    Collection<SurveyDto> findAll();

    Optional<SurveyDto> findById(long id);

    SurveyDto add(SurveyDto surveyDto);

    Optional<SurveyDto> addQuestion(long surveyId, long questionId);

    Optional<SurveyDto> addQuestions(long surveyId, Collection<Long> questionsIds);

    Optional<SurveyDto> removeQuestion(long surveyId, long questionId);

    Optional<SurveyDto> clearQuestions(long surveyId);

    boolean remove(long surveyId);
}
