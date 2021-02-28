package link.toocool.vacancydiary.mapper;

import link.toocool.vacancydiary.dto.vacancy.ContactDTO;
import link.toocool.vacancydiary.dto.vacancy.CreateEditVacancyDTO;
import link.toocool.vacancydiary.dto.vacancy.VacancyDTO;
import link.toocool.vacancydiary.entity.Contact;
import link.toocool.vacancydiary.entity.Vacancy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Component
public class VacancyMapper {

    public VacancyDTO dtoFromVacancy(Vacancy vacancy) {
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setId(vacancy.getId());
        vacancyDTO.setCompanyName(vacancy.getCompanyName());
        vacancyDTO.setExpectedSalary(vacancy.getExpectedSalary());
        vacancyDTO.setLink(vacancy.getLink());
        vacancyDTO.setPosition(vacancy.getPosition());
        vacancyDTO.setLastUpdate(vacancy.getLastUpdate());
        vacancyDTO.setStatus(vacancy.getStatus());
        Contact contact = vacancy.getRecruiterContact();
        if (nonNull(contact)) {
            vacancyDTO.setRecruiterContact(
                    new ContactDTO(contact.getEmail(), contact.getTelephone(), contact.getSkype()));
        }
        return vacancyDTO;
    }

    public Vacancy vacancyFromDto(CreateEditVacancyDTO createEditVacancyDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setLastUpdate(LocalDateTime.now());
        vacancy.setRecruiterContact(new Contact());

        return mapVacancyFromDto(vacancy, createEditVacancyDTO);
    }

    public Vacancy mapVacancyFromDto(Vacancy vacancy, CreateEditVacancyDTO createEditVacancyDTO) {
        vacancy.setCompanyName(createEditVacancyDTO.getCompanyName());
        vacancy.setPosition(createEditVacancyDTO.getPosition());
        vacancy.setExpectedSalary(createEditVacancyDTO.getExpectedSalary());
        vacancy.setLink(createEditVacancyDTO.getLink());
        vacancy.setStatus(createEditVacancyDTO.getStatus());

        if (nonNull(createEditVacancyDTO.getRecruiterContact())) {
            ContactDTO contactDTO = createEditVacancyDTO.getRecruiterContact();
            vacancy.getRecruiterContact().setEmail(contactDTO.getEmail());
            vacancy.getRecruiterContact().setSkype(contactDTO.getSkype());
            vacancy.getRecruiterContact().setTelephone(contactDTO.getTelephone());
        }
        return vacancy;
    }
}
