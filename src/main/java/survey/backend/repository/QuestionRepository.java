package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.Question;
import survey.backend.repository.entities.Trainee;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
