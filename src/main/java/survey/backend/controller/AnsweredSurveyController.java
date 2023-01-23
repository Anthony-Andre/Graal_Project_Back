package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import survey.backend.dto.AnsweredSurveyDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.service.impl.AnsweredSurveyService;

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

}
