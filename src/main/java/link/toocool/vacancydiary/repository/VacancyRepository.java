package link.toocool.vacancydiary.repository;

import link.toocool.vacancydiary.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}
