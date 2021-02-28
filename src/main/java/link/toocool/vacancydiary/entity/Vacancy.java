package link.toocool.vacancydiary.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Entity
@Table(name = "vacancies")
@Getter
@Setter
@NoArgsConstructor
public class Vacancy extends BasicEntity {

    @Column(name = "company_name", nullable = false)
    private String companyName;

    private String position;

    private Double expectedSalary;

    private String link;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    private Contact recruiterContact;

    @Enumerated(EnumType.STRING)
    private VacancyStatus status;

    private LocalDateTime lastUpdate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setStatus(VacancyStatus statusParam) {
        if ((nonNull(this.status) && !this.status.equals(statusParam))
                || (isNull(this.status) && nonNull(statusParam))) {
            lastUpdate = LocalDateTime.now();
        }
    }
}
