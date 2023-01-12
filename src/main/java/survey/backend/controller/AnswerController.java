package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.dto.AnswerDto;
import survey.backend.dto.QuestionDto;
import survey.backend.error.errors.NoDataFoundError;
import survey.backend.service.impl.AnswerService;
import survey.backend.service.impl.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public Collection<AnswerDto> findAll() {
        return this.answerService.findAll();
    }

    @GetMapping("/{id}")
    public AnswerDto findById(@PathVariable("id") long id){
        return answerService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("answer", id));
    }
}
