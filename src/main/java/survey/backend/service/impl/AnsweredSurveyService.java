package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.AnsweredSurveyDto;
import survey.backend.dto.ResponseDto;
import survey.backend.repository.AnsweredSurveyRepository;
import survey.backend.repository.ResponseRepository;
import survey.backend.repository.SurveyRepository;
import survey.backend.repository.TraineeRepository;
import survey.backend.repository.entities.AnsweredSurvey;
import survey.backend.repository.entities.Question;
import survey.backend.repository.entities.Response;
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AnsweredSurveyService implements survey.backend.service.AnsweredSurveyService {
    @Autowired
    private AnsweredSurveyRepository answeredSurveyRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<AnsweredSurveyDto> findAll() {
        return StreamUtils.toStream(this.answeredSurveyRepository.findAll())
                .map(questionEntity -> modelMapper.map(questionEntity, AnsweredSurveyDto.class))
                .toList();
    }

    @Override
    public Optional<AnsweredSurveyDto> findById(Long id) {
        return this.answeredSurveyRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, AnsweredSurveyDto.class));
    }

    @Override
    public AnsweredSurveyDto add(AnsweredSurveyDto answeredSurveyDto) {
        AnsweredSurvey answeredSurveyEntity = modelMapper.map(answeredSurveyDto, AnsweredSurvey.class);
        this.answeredSurveyRepository.save(answeredSurveyEntity);
        return modelMapper.map(answeredSurveyEntity, AnsweredSurveyDto.class);
    }

    @Override
    public boolean remove(Long answeredSurveyId) {
        Optional<AnsweredSurvey> oAnsweredSurvey = this.answeredSurveyRepository.findById(answeredSurveyId);
        if (oAnsweredSurvey.isPresent()) {
            this.answeredSurveyRepository.delete(oAnsweredSurvey.get());
            return true;
        }
        return false;
    }

}
