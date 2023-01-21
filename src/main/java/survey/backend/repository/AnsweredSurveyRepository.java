package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.AnsweredSurvey;

public interface AnsweredSurveyRepository extends CrudRepository<AnsweredSurvey, Long> {
}
