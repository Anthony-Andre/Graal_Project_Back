package survey.backend.service;

import survey.backend.dto.AnswerDto;
import survey.backend.dto.SurveyDto;

import java.util.Collection;
import java.util.Optional;

public interface AnswerService {
    Collection<AnswerDto> findAll();

    Optional<AnswerDto> findById(long id);
}
