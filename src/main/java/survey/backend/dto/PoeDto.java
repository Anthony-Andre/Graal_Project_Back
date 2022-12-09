package survey.backend.dto;

import lombok.*;
import survey.backend.entities.Poe;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PoeDto {

    public enum PoeType{
        POEI, POEC
    }

    private Long id;
    private String title;
    private Date beginDate;
    private Date endDate;
    private survey.backend.entities.PoeType type;

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
