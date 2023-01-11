package survey.backend.repository.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    //@Column(nullable = false)
    //private Long questionID;

    //@Builder.Default
    //@OneToMany
    //@JoinColumn(name="question_id")
    //private Question question;

}
