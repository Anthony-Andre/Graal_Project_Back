package survey.backend.service;

import survey.backend.dto.AnsweredSurveyDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AnsweredSurveyService {

    Collection<AnsweredSurveyDto> findAll();

   Optional<AnsweredSurveyDto> findById(Long id);

   AnsweredSurveyDto add(AnsweredSurveyDto answeredSurveyDto);

   /**
    * search survey with criteria surveyId, traineeId ;
    * @param surveyId
    * @param traineeId
    * @return survey with this surveyId and this traineeId;
    * empty set if no survey found with these criteria
    */
    List<AnsweredSurveyDto> search(Long surveyId, Long traineeId);

   boolean remove(Long answerId);

}
