package survey.backend.repository.entities;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@Entity
public class AnsweredSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @OneToMany(mappedBy = "answeredSurvey", cascade = CascadeType.ALL)
    private List<Response> responses;



}
