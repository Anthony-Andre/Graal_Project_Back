package survey.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.dto.PoeDto;
import survey.backend.dto.TraineeDto;
import survey.backend.entities.Poe;
import survey.backend.entities.Trainee;
import survey.backend.repository.PoeRepository;

import java.util.Optional;

@Service
public class PoeService {

    @Autowired
    PoeRepository repository;

    public Iterable<Poe> findAll() {
        return this.repository.findAll();
    }

    public Optional<Poe> findById(int id) {
        return this.repository.findById((long) id);
    }

    public boolean delete(int id) {
        Optional<Poe> oPoe = this.repository.findById((long) id);
        if (oPoe.isPresent()) {
            this.repository.delete(oPoe.get());
            return true;
        }
        return false;
    }
    public Optional<Poe> update(PoeDto poeDto) {
        Poe poe = poeDto.toPoe();
        Optional<Poe> oPoe = this.repository.findById(poe.getId());
        if (oPoe.isPresent()) {
            this.repository.save(poe);
            return Optional.of(poe);
        }
        return Optional.empty();
    }

}
