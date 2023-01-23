package survey.backend.service;

import survey.backend.dto.AnsweredSurveyDto;

import java.util.Collection;
import java.util.Optional;

public interface AnsweredSurveyService {

    Collection<AnsweredSurveyDto> findAll();

   Optional<AnsweredSurveyDto> findById(Long id);

   AnsweredSurveyDto add(AnsweredSurveyDto answeredSurveyDto);

   boolean remove(Long answerId);

}
