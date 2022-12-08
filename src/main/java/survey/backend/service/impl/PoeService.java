package survey.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import survey.backend.entities.Poe;
import survey.backend.repository.PoeRepository;

import java.util.Optional;

@Service
public class PoeService {

    @Autowired
    PoeRepository repository;

    public Iterable<Poe> findAll() {
        return this.repository.findAll();
    }

    public boolean delete(int id) {
        Optional<Poe> oPoe = this.repository.findById((long) id);
        if (oPoe.isPresent()) {
            this.repository.delete(oPoe.get());
            return true;
        }
        return false;
    }
}
