package link.toocool.vacancydiary.dto.vacancy;

import link.toocool.vacancydiary.entity.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEditVacancyDTO {

    @NotEmpty(message = "Company name should be specified.")
    private String companyName;

    private String position;

    private Double expectedSalary;

    private String link;

    private VacancyStatus status;

    private ContactDTO recruiterContact;
}
