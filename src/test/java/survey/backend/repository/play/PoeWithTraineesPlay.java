package survey.backend.repository.play;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import survey.backend.repository.PoeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use app db
public class PoeWithTraineesPlay {

    @Autowired
    PoeRepository poeRepository;

    @Test
    void readPoeWithTrainees() {
        var poe = poeRepository.findById(1L).get();
        // select poe with id 1

        System.out.println(poe);

        System.out.println(poe.getTrainees());
    }

}
