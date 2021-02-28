package link.toocool.vacancydiary.querydsl.bean;

import link.toocool.vacancydiary.entity.VacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacanciesQueryBean extends QueryBasicBean {

    private VacancyStatus status;

    private String companyName;
}
