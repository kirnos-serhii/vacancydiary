package link.toocool.vacancydiary.service.impl;

import link.toocool.vacancydiary.dto.scroll.ScrollPageDTO;
import link.toocool.vacancydiary.dto.vacancy.CreateEditVacancyDTO;
import link.toocool.vacancydiary.dto.vacancy.VacancyDTO;
import link.toocool.vacancydiary.entity.User;
import link.toocool.vacancydiary.entity.Vacancy;
import link.toocool.vacancydiary.exception.RestException;
import link.toocool.vacancydiary.mapper.VacancyMapper;
import link.toocool.vacancydiary.querydsl.VacanciesQueryDsl;
import link.toocool.vacancydiary.querydsl.bean.VacanciesQueryBean;
import link.toocool.vacancydiary.repository.UserRepository;
import link.toocool.vacancydiary.repository.VacancyRepository;
import link.toocool.vacancydiary.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private VacancyMapper vacancyMapper;

    private UserRepository userRepository;

    private VacancyRepository vacancyRepository;

    private VacanciesQueryDsl vacanciesQueryDsl;

    @Override
    @Transactional(readOnly = true)
    public ScrollPageDTO<VacancyDTO> getVacanciesByUserId(Long userId, VacanciesQueryBean queryBean) {
        Page<Vacancy> vacancies = vacanciesQueryDsl.executePagebale(queryBean);

        return new ScrollPageDTO<>(vacancies
                .stream()
                .map(vacancyMapper::dtoFromVacancy)
                .collect(Collectors.toList()),
                vacancies.getTotalPages(),
                vacancies.getPageable().getPageNumber(),
                vacancies.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true)
    public VacancyDTO getVacancy(Long userId, Long vacancyId) {
        User user = getUser(userId);
        return vacancyMapper.dtoFromVacancy(getVacancyGyUser(user, vacancyId));
    }

    @Override
    @Transactional
    public VacancyDTO createVacancy(Long userId, CreateEditVacancyDTO createEditVacancyDTO) {
        User user = getUser(userId);
        Vacancy vacancy = vacancyMapper.vacancyFromDto(createEditVacancyDTO);
        vacancy.setUser(user);

        return vacancyMapper.dtoFromVacancy(vacancyRepository.save(vacancy));
    }

    @Override
    @Transactional
    public VacancyDTO editVacancy(Long userId, Long vacancyId, CreateEditVacancyDTO createEditVacancyDTO) {
        User user = getUser(userId);
        Vacancy vacancy = getVacancyGyUser(user, vacancyId);

        vacancyMapper.mapVacancyFromDto(vacancy, createEditVacancyDTO);

        return vacancyMapper.dtoFromVacancy(vacancy);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RestException("No user by id.", HttpStatus.NOT_FOUND));
    }

    private Vacancy getVacancyGyUser(User user, Long vacancyId) {
        return user.getVacancies()
                .stream()
                .filter(vacancy1 -> vacancy1.getId().equals(vacancyId))
                .findFirst()
                .orElseThrow(() -> new RestException("No vacancy by id.", HttpStatus.NOT_FOUND));
    }
}
