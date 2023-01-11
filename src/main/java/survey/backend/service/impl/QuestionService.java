package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.QuestionDto;
import survey.backend.repository.QuestionRepository;
<<<<<<< HEAD
import survey.backend.repository.entities.Question;
=======
>>>>>>> c2e2db5 (Question Controller and DTO)
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.Optional;

@Service
public class QuestionService implements survey.backend.service.QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Collection<QuestionDto> findAll() {
        return StreamUtils.toStream(this.questionRepository.findAll())
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class))
                .toList();
    }

    @Override
    public Optional<QuestionDto> findById(long id) {
<<<<<<< HEAD
        return this.questionRepository.findById(id)
                .map(questionEntity -> modelMapper.map(questionEntity, QuestionDto.class));
=======
        return Optional.empty();
>>>>>>> c2e2db5 (Question Controller and DTO)
    }

    @Override
    public QuestionDto add(QuestionDto questionDto) {
        Question questionEntity = modelMapper.map(questionDto, Question.class);
        this.questionRepository.save(questionEntity);
        return modelMapper.map(questionEntity, QuestionDto.class);
    }

    @Override
<<<<<<< HEAD
    public boolean remove(long questionId) {
        Optional<Question> oQuestion = this.questionRepository.findById(questionId);
        if (oQuestion.isPresent()) {
            this.questionRepository.delete(oQuestion.get());
            return true;
        }
=======
    public QuestionDto add(QuestionDto surveyDto) {
        return null;
    }

    @Override
    public boolean remove(long surveyId) {
>>>>>>> c2e2db5 (Question Controller and DTO)
        return false;
    }
}