package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import survey.backend.dto.AnswerDto;
import survey.backend.dto.QuestionDto;
import survey.backend.repository.AnswerRepository;
import survey.backend.repository.QuestionRepository;
import survey.backend.util.StreamUtils;

import java.util.Collection;
import java.util.Optional;

public class AnswerService implements survey.backend.service.AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Collection<AnswerDto> findAll() {
        return StreamUtils.toStream(this.answerRepository.findAll())
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class))
                .toList();
    }

    @Override
    public Optional<AnswerDto> findById(long id) {
        return this.answerRepository.findById(id)
                .map(answerEntity -> modelMapper.map(answerEntity, AnswerDto.class));
    }
}
