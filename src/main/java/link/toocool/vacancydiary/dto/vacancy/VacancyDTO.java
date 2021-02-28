package link.toocool.vacancydiary.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonFormat;
import link.toocool.vacancydiary.entity.VacancyStatus;
import link.toocool.vacancydiary.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDTO {

    private Long id;

    private String companyName;

    private String position;

    private Double expectedSalary;

    private String link;

    private VacancyStatus status;

    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime lastUpdate;

    private ContactDTO recruiterContact;
}
