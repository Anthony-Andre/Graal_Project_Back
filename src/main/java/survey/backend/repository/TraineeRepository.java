package survey.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import survey.backend.entities.Trainee;

public interface TraineeRepository extends CrudRepository<Trainee, Long> {

    @Query(
            value = "SELECT id, last_name, first_name, email, phone_number, birth_date FROM trainee WHERE last_name = :lastName AND first_name = :firstName ",
            nativeQuery = true) // native signifie qu'on veut écrire du SQL standard
    public Iterable<Trainee> listByLastNameAndFirstName(@Param(value = "lastName") String lastName, @Param(value = "firstName") String firstName);



    @Query(
            value =  "SELECT t FROM Trainee t WHERE t.lastName = :lastName AND t.firstName = :firstName"
    ) // Utilisation du HQL ici (Hibernate Query Language)
    public Iterable<Trainee> byLastNameAndFirstName(@Param(value= "lastName") String lastName, @Param(value = "firstName") String firstName);


    public Iterable<Trainee> findByFirstName(String firstName);

    public Iterable<Trainee> findByLastName(String lastName);

    public Iterable<Trainee> findByEmail(String email);


}

