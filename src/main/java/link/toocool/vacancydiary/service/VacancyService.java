package link.toocool.vacancydiary.service;

import link.toocool.vacancydiary.dto.scroll.ScrollPageDTO;
import link.toocool.vacancydiary.dto.vacancy.CreateEditVacancyDTO;
import link.toocool.vacancydiary.dto.vacancy.VacancyDTO;
import link.toocool.vacancydiary.querydsl.bean.VacanciesQueryBean;

public interface VacancyService {

    ScrollPageDTO<VacancyDTO> getVacanciesByUserId(Long userId, VacanciesQueryBean queryBean);

    VacancyDTO getVacancy(Long userId, Long vacancyId);

    VacancyDTO createVacancy(Long userId, CreateEditVacancyDTO createEditVacancyDTO);

    VacancyDTO editVacancy(Long userId, Long vacancyId, CreateEditVacancyDTO createEditVacancyDTO);
}
