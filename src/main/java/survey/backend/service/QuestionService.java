package survey.backend.service;

import survey.backend.dto.QuestionDto;

import java.util.Collection;
import java.util.Optional;

public interface QuestionService {

    Collection<QuestionDto> findAll();

    Optional<QuestionDto> findById(long id);

    Optional<QuestionDto> search(String title);

    QuestionDto add(QuestionDto questionDto);

    boolean remove(long questionId);

}