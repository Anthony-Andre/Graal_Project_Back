package survey.backend.repository.entities;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class AnsweredSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

//    @ElementCollection
//    @CollectionTable(name = "answers", joinColumns = @JoinColumn(name = "answeredSurvey_id"))
//    @Column(name = "answers", nullable = false)
//    private List<Response> responses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answeredSurvey_id")
    private List<Response> responses;

}
