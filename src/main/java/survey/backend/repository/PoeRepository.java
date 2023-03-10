package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import survey.backend.repository.entities.Poe;

import java.util.Date;
import java.util.List;

public interface PoeRepository extends CrudRepository<Poe, Long> {
    @Query("select p from Poe p "
            + " where p.endDate  between "
            + " :dateFirst and :dateLast "
    )
    List<Poe> findByEndingInRange(Date dateFirst, Date dateLast);

    List<Poe> findByEndDateBetween(Date dateFirst, Date dateLast);
}



