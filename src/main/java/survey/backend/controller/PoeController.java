package survey.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import survey.backend.dto.PoeDto;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Poe;
import survey.backend.entities.Trainee;
import survey.backend.error.NoDataFoundError;
import survey.backend.service.impl.PoeService;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/poe")
public class PoeController {

    @Autowired
    private PoeService service;

    @GetMapping
    public Iterable<Poe> findAll(){
        return this.service.findAll();
    }

    @PutMapping
    public Poe update(@RequestBody PoeDto poeDto) {
        return this.service.update(poeDto)
                .orElseThrow(() -> NoDataFoundError.withId("Trainee", Math.toIntExact(poeDto.getId())));
    }


//    @GetMapping
//    public Set<PoeDto>list() {
//        return Set.of(
//                PoeDto.builder()
//                        .id(1)
//                        .title("POEI")
//                        .beginDate(LocalDate.of(2022,10,24))
//                        .endDate(LocalDate.of(2023,1,27))
//                        .PoeType(PoeDto.PoeType.POEI)
//                        .build(),
//                PoeDto.builder()
//                        .id(2)
//                        .title("POEC")
//                        .beginDate(LocalDate.of(2022,10,24))
//                        .endDate(LocalDate.of(2023,1,27))
//                        .PoeType(PoeDto.PoeType.POEC)
//                        .build()
//        );
//    }
//
//    @GetMapping("{id}")
//    public Optional<PoeDto> find(@PathVariable("id") int id) {
////        return Optional.empty();
//        return Optional.of(PoeDto.builder()
//                .id(id)
//                .build());
//    }
//
//    @GetMapping("search")
//    public Set<PoeDto> search(
//            @RequestParam(name = "title", required = false) String title
//    ) {
//        return Set.of(
//                PoeDto.builder()
//                        .title("POEI")
//                        .build()
//        );
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public PoeDto addPoe(@RequestBody PoeDto poeDto){
//        poeDto.setId(54321);
//        return poeDto;
//    }

}
