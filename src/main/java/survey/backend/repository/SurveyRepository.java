package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
