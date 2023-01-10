package survey.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.TraineeDto;
import survey.backend.repository.entities.Trainee;
import survey.backend.repository.TraineeRepository;
import survey.backend.util.StreamUtils;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class TraineeService implements survey.backend.service.TraineeService {

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<TraineeDto> findAll() {
        //  return this.traineeRepository.findAll();
        return StreamUtils.toStream(this.traineeRepository.findAll())
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                .toList();
    }

    @Override
    public Optional<TraineeDto> findById(long id) {
        return this.traineeRepository.findById(id)
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class));
    }

    @Override
    public Iterable<TraineeDto> search(String lastname, String firstname) {
        if (lastname != null && firstname != null) {
            return this.traineeRepository.listByLastNameAndFirstName(lastname, firstname)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
        }

        if (lastname != null) {
           return this.traineeRepository.findByLastName(lastname)
                   .stream()
                   .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                   .toList();
        }

        return this.traineeRepository.findByFirstName(firstname)
                .stream()
                .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                .toList();

    }

    @Override
    public TraineeDto add(TraineeDto traineeDto) {
        Trainee traineeEntity = modelMapper.map(traineeDto, Trainee.class);
        this.traineeRepository.save(traineeEntity);
        TraineeDto traineeDtoResponse = modelMapper.map(traineeEntity, TraineeDto.class);
        return traineeDtoResponse;
    }

    @Override
    public Optional<TraineeDto> update(TraineeDto traineeDto) {
        return this.traineeRepository.findById(traineeDto.getId())
                .map(traineeEntity -> {
                    modelMapper.map(traineeDto, traineeEntity);
                    traineeRepository.save(traineeEntity);
                    return modelMapper.map(traineeEntity, TraineeDto.class);
                });
    }

    @Override
    public boolean delete(long id) {
        Optional<Trainee> oTrainee = this.traineeRepository.findById((long) id);
        if (oTrainee.isPresent()) {
            this.traineeRepository.delete(oTrainee.get());
            return true;
        }
        return false;
    }
}