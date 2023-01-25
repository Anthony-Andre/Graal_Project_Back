package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.QuestionDto;
import survey.backend.error.errors.NoDataFoundError;
import survey.backend.service.impl.QuestionService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping
    public Collection<QuestionDto> findAll() {
        return this.questionService.findAll();
    }

    @GetMapping("/{id}")
    public QuestionDto findById(@PathVariable("id") long id){
        return questionService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("question", id));
    }

//    @GetMapping("search")
//    public Optional<QuestionDto> search(
//            @RequestParam(name = "title", required = false) String title
//    ) {
//    }

    @PatchMapping
    public QuestionDto add(@Valid @RequestBody QuestionDto questionDto){
        return questionService.add(questionDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable("id") long id) {
        if (!questionService.remove(id)){
            throw NoDataFoundError.withId("question", id);
        }
    }




}
