package survey.backend.repository.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import survey.backend.dto.enums.AnswerType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private AnswerType answerType;

    @Builder.Default
    @OneToMany
    @JoinColumn(name="question_id")
    private Set<Answer> answers = new HashSet<>();

}