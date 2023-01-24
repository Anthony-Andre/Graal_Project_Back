package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.AnsweredSurveyDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.SurveyDto;
import survey.backend.error.errors.NoDataFoundError;
import survey.backend.service.impl.AnsweredSurveyService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/answered-survey")
public class AnsweredSurveyController {

    @Autowired
    private AnsweredSurveyService answeredSurveyService;

    @GetMapping
    public Collection<AnsweredSurveyDto> findAll() {
        return this.answeredSurveyService.findAll();
    }

    @PatchMapping
    public AnsweredSurveyDto add(@Valid @RequestBody AnsweredSurveyDto answeredSurveyDto){
        return answeredSurveyService.add(answeredSurveyDto);
    }

    @GetMapping("/{id}")
    public AnsweredSurveyDto findById(@PathVariable("id") long id){
        return answeredSurveyService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("answeredSurvey", id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        if (!answeredSurveyService.remove(id)){
            throw NoDataFoundError.withId("survey", id);
        }
    }

}