package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.Answer;
import survey.backend.repository.entities.Poe;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
}
