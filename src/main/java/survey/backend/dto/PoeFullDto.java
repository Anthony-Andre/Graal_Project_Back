package survey.backend.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import survey.backend.repository.entities.Poe;
import survey.backend.repository.entities.Trainee;

import java.util.Date;
import java.util.List;
import java.util.Set;


    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class PoeFullDto extends PoeDto {
        private Set<TraineeDto> trainees;
        private Boolean mailSend;
    }

