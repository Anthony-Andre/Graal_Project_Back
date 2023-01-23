package survey.backend.repository.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.dto.PoeFullDto;
import survey.backend.dto.enums.Level;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(length = 4)
    private PoeType poeType;

    @ManyToMany
    @JoinTable(name = "survey_contains_question",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<Question>() {
    };

    @ManyToMany
    @JoinTable(name = "survey_contains_poes",
            joinColumns = @JoinColumn(name = "survey_id"),
            inverseJoinColumns = @JoinColumn(name = "poe_id")
    )
    private List<Poe> poes = new ArrayList<>();
}