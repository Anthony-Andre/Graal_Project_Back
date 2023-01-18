package survey.backend.repository.entities;

import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import survey.backend.dto.enums.AnswerType;

import javax.persistence.*;
import java.util.*;

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

    @ElementCollection
    @CollectionTable(name = "answer_purposed", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "answer_purposed", nullable = false)
    private List<String> answersProposed;



}