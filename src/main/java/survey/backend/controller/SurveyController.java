package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.SurveyDto;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.impl.SurveyService;


import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/survey")
public class SurveyController {

//    TODO !!!
    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public Collection<SurveyDto> findAll() {
        return this.surveyService.findAll();
    }

    @GetMapping("/{id}")
    public SurveyDto findById(@PathVariable("id") long id){
        return surveyService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("survey", id));
    }

    @PatchMapping
    public SurveyDto add(@Valid @RequestBody SurveyDto surveyDto){
        return surveyService.add(surveyDto);
    }

    @PatchMapping("/{surveyId}/addQuestion/{questionId}")
    public SurveyDto addQuestion(
            @PathVariable("surveyId") long surveyId,
            @PathVariable("questionId") long questionId
    )
    {
        return surveyService.addQuestion(surveyId, surveyId)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("survey", surveyId, "question", questionId)));
    }

    @PatchMapping("/{surveyId}/addQuestions")
    public SurveyDto addQuestions(
            @PathVariable("surveyId") long surveyId,
            @RequestBody List<Long> questionIds
    ){
        return surveyService.addQuestions(surveyId, questionIds)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("survey",surveyId),
                        Map.of("Questions", questionIds)
                ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        if (!surveyService.remove(id)){
            throw NoDataFoundError.withId("survey", id);
        }
    }
}
