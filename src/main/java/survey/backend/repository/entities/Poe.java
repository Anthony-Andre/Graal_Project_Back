package survey.backend.repository.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "trainees")
@Getter @Setter
public class Poe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 150, nullable = false)
    private String title;

    @Column(name = "begin_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Column(name = "end_date", nullable =false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    // @Column(name="poe_type", length = 10, nullable = false)
    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private PoeType type;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "poe_id")
    private Set<Trainee> trainees = new HashSet<>();

}
