package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.SurveyDto;
import survey.backend.repository.QuestionRepository;
import survey.backend.repository.SurveyRepository;
import survey.backend.repository.TraineeRepository;
import survey.backend.repository.entities.Poe;
import survey.backend.repository.entities.Survey;
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.Optional;

public class SurveyService implements survey.backend.service.SurveyService{

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<SurveyDto> findAll() {
        return StreamUtils.toStream(surveyRepository.findAll())
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyDto.class))
                .toList();
        //return null;
    }

    @Override
    public Optional<SurveyDto> findById(long id) {
        return surveyRepository.findById(id)
                .map(surveyEntity -> modelMapper.map(surveyEntity, SurveyDto.class));
        //return Optional.empty();
    }

    @Override
    public SurveyDto add(SurveyDto surveyDto) {
        Survey surveyEntity = modelMapper.map(surveyDto, Survey.class);
        surveyRepository.save(surveyEntity);
        return modelMapper.map(surveyEntity, SurveyDto.class);
        //return null;
    }

    @Override
    public Optional<SurveyDto> addQuestion(long surveyId, long questionId) {
        return surveyRepository.findById(surveyId)
                .flatMap(surveyEntity -> surveyRepository.findById(questionId)
                        .map(questionEntity -> {
                            // add trainee to poe<
                            surveyEntity.getQuestions().add(questionEntity);
                            // sync with DB
                            surveyRepository.save(surveyEntity);
                            // return poe updated
                            return modelMapper.map(surveyEntity, SurveyDto.class);
                        })
                );
        //return Optional.empty();
    }

    @Override
    public Optional<SurveyDto> addQuestions(long surveyIds, Collection<Long> questionsIds) {
        return surveyRepository.findById(surveyIds)
                .flatMap(surveyEntity -> {
                    var questionEntities = StreamUtils.toStream(questionRepository.findAllById(questionsIds))
                            .toList();
                    if (questionsIds.size() != questionEntities.size()) {
                        return Optional.empty();
                    }
                    // add trainees in poe
                    surveyEntity.getQuestions().addAll(questionEntities);
                    // sync with DB
                    surveyRepository.save(surveyEntity);
                    // return poe updated as DTO
                    return Optional.of(modelMapper.map(surveyEntity, SurveyDto.class));
                });
        //return Optional.empty();
    }

    @Override
    public boolean remove(long surveyId) {
        return surveyRepository.findById(surveyId)
                .map(surveyEntity -> {
                    surveyRepository.delete(surveyEntity);
                    return true;
                })
                .orElse(false);
        //return false;
    }
}
