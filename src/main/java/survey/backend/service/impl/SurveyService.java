package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import survey.backend.dto.PoeDto;
import survey.backend.dto.SurveyDto;
import survey.backend.repository.SurveyRepository;
import survey.backend.repository.TraineeRepository;
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.Optional;

public class SurveyService implements survey.backend.service.SurveyService{

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<SurveyDto> findAll() {
        return null;
    }

    @Override
    public Optional<SurveyDto> findById(long id) {
        return Optional.empty();
    }

    @Override
    public SurveyDto add(SurveyDto surveyDto) {
        return null;
    }

    @Override
    public Optional<SurveyDto> addQuestion(long surveyId, long questionId) {
        return Optional.empty();
    }

    @Override
    public Optional<SurveyDto> addQuestions(long surveyId, Collection<Long> surveyIds) {
        return Optional.empty();
    }

    @Override
    public boolean remove(long surveyId) {
        return false;
    }
}
