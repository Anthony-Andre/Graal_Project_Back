package survey.backend.service.impl;

import survey.backend.dto.QuestionDto;
import survey.backend.dto.SurveyDto;

import java.util.Collection;
import java.util.Optional;

public class QuestionService implements survey.backend.service.QuestionService {
    @Override
    public Collection<SurveyDto> findAll() {
        return null;
    }

    @Override
    public Optional<SurveyDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<QuestionDto> search(String title) {
        return Optional.empty();
    }

    @Override
    public SurveyDto add(SurveyDto surveyDto) {
        return null;
    }

    @Override
    public boolean remove(long surveyId) {
        return false;
    }
}
