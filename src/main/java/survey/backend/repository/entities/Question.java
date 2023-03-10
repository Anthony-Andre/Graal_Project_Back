package survey.backend.repository.entities;

import lombok.Getter;
import lombok.Setter;
import survey.backend.repository.enums.AnswerType;

import javax.persistence.*;

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

}