package survey.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("api/trainee")
public class TraineeController {

    @Autowired // DI (Dependency Injection)
    private TraineeService traineeService;

    @GetMapping
    public Set<TraineeDto> list() {
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Stallone")
                        .firstName("Sly")
                        .birthDate(LocalDate.of(1946,7,6))
                        .build(),
                TraineeDto.builder()
                        .id(3)
                        .lastName("Apeupres")
                        .firstName("Jean-Michel")
                        .birthDate(LocalDate.of(1975,3,7))
                        .build(),
                TraineeDto.builder()
                        .id(15)
                        .lastName("Cage")
                        .firstName("Nicolas")
                        .birthDate(LocalDate.of(1964,1,7))
                        .build(),
                TraineeDto.builder()
                        .id(22)
                        .lastName("André")
                        .firstName("Anthony")
                        .birthDate(LocalDate.of(1992,4,28))
                        .build(),
                TraineeDto.builder()
                        .id(35)
                        .lastName("Tentacule")
                        .firstName("Carlos")
                        .birthDate(LocalDate.of(1922,9,2))
                        .build()
        );
    }

    /**
     * found a trainee by its id
     * route : /api/trainee/{id}
     *
     * @param id
     * @return a trainee
     */
    @GetMapping("{id}")
    public TraineeDto getById(@PathVariable("id") int id) {
        Optional<TraineeDto> optTraineeDto = traineeService.findById(id);
        if(optTraineeDto.isPresent()){
            return optTraineeDto.get();
        } else {
            throw new IllegalArgumentException(
                    "Trainee with id " + id + " not found"
            );
        }

//        return Optional.empty();
//        return Optional.of(TraineeDto.builder()
//                .id(id)
//                .lastName("Stallone")
//                .firstName("Sly")
//                .birthDate(LocalDate.of(1946,7,6))
//                .build());
    }

    /**
     * search trianees with criteria
     * route: /api/trainee/search?fn=some_firstname&ln=some_lastname
     *
     * @param firstname (optionnal)
     * @param lastname  (optionnal)
     * @return trainees coresponding
     */
    @GetMapping("search")
    public Set<TraineeDto> search(
            @RequestParam(name = "fn", required = false) String firstname,
            @RequestParam(name = "ln", required = false) String lastname
    ) {
        return Set.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName(Objects.isNull(lastname) ? "Found" : lastname)
                        .firstName(Objects.isNull(firstname) ? "Sly" : firstname)
                        .build(),
                TraineeDto.builder()
                        .id(3)
                        .lastName("Found")
                        .firstName("Jean-Michel")
                        .build(),
                TraineeDto.builder()
                        .id(15)
                        .lastName("Found")
                        .firstName("Nicolas")
                        .build(),
                TraineeDto.builder()
                        .id(22)
                        .lastName("Found")
                        .firstName("Anthony")
                        .build()
        );
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto addTrainee(@RequestBody TraineeDto traineeDto){
        return traineeService.add(traineeDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable ("id") int id){
        // TODO: delete this object if exists
    }

//    @PatchMapping
//    public  Set<TraineeDto> patchTrainee(@PathVariable("id") int id){
//
//    }

}
