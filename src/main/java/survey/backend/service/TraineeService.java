package survey.backend.service;

import survey.backend.dto.TraineeDto;

import java.util.Optional;
import java.util.Set;

public interface TraineeService {


    // findAll

    Set<TraineeDto> findAll();

    // findById

    Optional<TraineeDto> findById(int id);

    // search

    Set<TraineeDto> search(String lastName, String firstName);

    // add

    TraineeDto add(TraineeDto traineeDto);

    // update

    TraineeDto update(TraineeDto traineeDto);

    // delete

    void delete(int id);
}
