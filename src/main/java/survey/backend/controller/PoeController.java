package survey.backend.controller;


import com.mailjet.client.errors.MailjetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.PoeFullDto;
import survey.backend.service.PoeService;
import survey.backend.error.errors.NoDataFoundError;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    private PoeService poeService;



//    @GetMapping
//    public Iterable<PoeFullDto> findAll(){
//        return this.service.findAll();
//    }
//
//    @GetMapping("/fullDetails")
//    public Iterable<PoeFullDto> findAllFullDetails(){
//        return this.service.findAllFullDetails();²
//    }
//
//    @GetMapping("{id}")
//    public Poe getById(@PathVariable("id") int id) {
//        Optional<PoeFullDto> optPoe = service.findById(id);
//        if (optPoe.isPresent()) {
//            return optPoe.get().toPoe();
//        } else {
//            throw NoDataFoundError.withId("Poe", id);
//        }
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PoeDto add(@Valid @RequestBody PoeDto poeDto) {
//        return this.service.add(poeDto);
//    }
//
//    @PutMapping
//    public Poe update(@RequestBody PoeFullDto poeFullDto) {
//        return this.service.update(poeFullDto)
//                .orElseThrow(() -> NoDataFoundError.withId("Poe", Math.toIntExact(poeFullDto.getId()))).toPoe();
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("id") int id) {
//        if (!service.delete(id)) {
//            throw NoDataFoundError.withId("Poe", id);
//        }
//    }
//
//    @PatchMapping("/{poeId}/addTrainee/{traineeId}")
//    public PoeFullDto addTrainee(
//            @PathVariable("poeId") long poeId,
//            @PathVariable("traineeId") long traineeId)
//    {
//        return service.addTrainee(poeId, traineeId)
//                .orElseThrow(() -> {
//                    throw NoDataFoundError.withIds("Poe or Trainee", poeId, traineeId);
//                });
//    }
//
//    @PatchMapping("/{poeId}/addTrainees")
//    public PoeFullDto addTrainees(
//            @PathVariable("poeId") long poeId,
//            @RequestBody List<Long> traineeIds
//    ){
//        return service.addTrainees(poeId, traineeIds)
//                .orElseThrow(() -> {
//                    throw NoDataFoundError.withIds("Poe or trainees",
//                            poeId);
//                });
//    }

    @GetMapping
    public Collection<PoeFullDto> findAll() {
        return this.poeService.findAll();
    }

    @GetMapping("/{id}")
    public PoeFullDto findById(@PathVariable("id") long id){
        return poeService.findById(id)
                .orElseThrow(() -> NoDataFoundError.withId("poe", id));
    }

    @PatchMapping
    public PoeDto add(@Valid @RequestBody PoeDto poeDto){
        return poeService.add(poeDto);
    }

    @PutMapping
    public PoeFullDto update(@RequestBody PoeDto poeDto) {
        return this.poeService.update(poeDto)
                .orElseThrow(() -> NoDataFoundError.withId("Poe", Math.toIntExact(poeDto.getId())));
    }

    @PatchMapping("/{poeId}/addTrainee/{traineeId}")
    public PoeFullDto addTrainee(
            @PathVariable("poeId") long poeId,
            @PathVariable("traineeId") long traineeId
    )
    {
        return poeService.addTrainee(poeId, traineeId)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("poe", poeId, "trainee", traineeId)));
    }

    @PatchMapping("/{poeId}/addTrainees")
    public PoeFullDto addTrainees(
            @PathVariable("poeId") long poeId,
            @RequestBody List<Long> traineeIds
    ){
        return poeService.addTrainees(poeId, traineeIds)
                .orElseThrow(() -> NoDataFoundError.withIds(
                        Map.of("poe",poeId),
                        Map.of("trainees", traineeIds)
                ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        if (!poeService.remove(id)){
            throw NoDataFoundError.withId("poe", id);
        }
    }

    @PatchMapping("{poeId}/deleteTrainee/{traineeId}")
    public PoeFullDto deleteTrainee(
            @PathVariable("poeId") long poeId,
            @PathVariable("traineeId") long traineeId
    ) {
        Optional<PoeFullDto> optFullPoe = this.poeService.removeTrainee(poeId, traineeId);
        if (optFullPoe.isPresent()) {return optFullPoe.get();}
        throw NoDataFoundError.withId("Trainee", traineeId);
    }

    @PatchMapping("{poeId}/clearTrainees")
    public PoeFullDto clear (@PathVariable("poeId") long poeId) {
        Optional<PoeFullDto> optFullPoe = this.poeService.clearTrainees(poeId);
        if (optFullPoe.isPresent()) {return optFullPoe.get();}
        throw NoDataFoundError.withId("Poe", poeId);
    }

    @PostMapping("{poeId}/mailToTrainees")
    @ResponseStatus(HttpStatus.OK)
    public void mail (@PathVariable("poeId") long poeId) throws MailjetException {
        this.poeService.mail(poeId);
    }




}
