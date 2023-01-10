package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.repository.entities.Poe;
import survey.backend.repository.entities.PoeType;
import survey.backend.repository.entities.Trainee;

import java.util.Date;
import java.util.Set;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PoeDto {

    private Long id;
    private String title;
    private Date beginDate;
    private Date endDate;
    private PoeType type;

    public Poe toPoe() {
        Poe poe = new Poe();
        poe.setId(this.id);
        poe.setTitle(this.title);
        poe.setBeginDate(this.beginDate);
        poe.setEndDate(this.endDate);
        poe.setType(this.type);
        return poe;
    }

}
