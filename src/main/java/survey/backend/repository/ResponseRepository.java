package survey.backend.repository;

import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.Response;


public interface ResponseRepository extends CrudRepository<Response, Long> {
}
