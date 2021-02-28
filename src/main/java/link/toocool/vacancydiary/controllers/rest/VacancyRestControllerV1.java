package link.toocool.vacancydiary.controllers.rest;

import link.toocool.vacancydiary.dto.vacancy.CreateEditVacancyDTO;
import link.toocool.vacancydiary.querydsl.bean.VacanciesQueryBean;
import link.toocool.vacancydiary.service.VacancyService;
import link.toocool.vacancydiary.util.ControllerUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users/{userId}/vacancies")
@AllArgsConstructor
public class VacancyRestControllerV1 {

    private VacancyService vacancyService;

    @GetMapping("/{vacancyId}")
    public ResponseEntity<?> getVacancy(@PathVariable Long userId, @PathVariable Long vacancyId) {
        return ResponseEntity.status(HttpStatus.OK).body(vacancyService.getVacancy(userId, vacancyId));
    }

    @PostMapping
    public ResponseEntity<?> createVacancy(@PathVariable Long userId,
                                           @Valid @RequestBody CreateEditVacancyDTO createEditVacancyDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ControllerUtil.getErrorMessages(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyService.createVacancy(userId, createEditVacancyDTO));
    }

    @PutMapping("/{vacancyId}")
    public ResponseEntity<?> editVacancy(@PathVariable Long userId, @PathVariable Long vacancyId,
                                         @Valid @RequestBody CreateEditVacancyDTO createEditVacancyDTO,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ControllerUtil.getErrorMessages(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                vacancyService.editVacancy(userId, vacancyId, createEditVacancyDTO));
    }

    @GetMapping
    public ResponseEntity<?> getVacancies(@PathVariable Long userId,
                                          VacanciesQueryBean queryBean) {
        return ResponseEntity.ok(vacancyService.getVacanciesByUserId(userId, queryBean));
    }
}
