package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.QuestionDto;
import survey.backend.repository.QuestionRepository;
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
        return Optional.empty();
    }

    @Override
    public Optional<QuestionDto> search(String title) {
        return Optional.empty();
    }

    @Override
    public QuestionDto add(QuestionDto surveyDto) {
        return null;
    }

    @Override
    public boolean remove(long surveyId) {
        return false;
    }
}