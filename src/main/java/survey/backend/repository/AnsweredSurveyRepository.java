package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.repository.entities.AnsweredSurvey;
import survey.backend.repository.entities.Survey;
import survey.backend.repository.entities.Trainee;

import java.util.List;

public interface AnsweredSurveyRepository extends CrudRepository<AnsweredSurvey, Long> {

    @Query(
            value = "SELECT s FROM AnsweredSurvey s WHERE s.survey = :survey AND s.trainee = :trainee"
    )
    public List<AnsweredSurvey> bySurveyAndTrainee (@Param(value = "survey") Survey survey, @Param(value = "trainee") Trainee trainee);

}
