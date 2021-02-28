package link.toocool.vacancydiary.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{userId}")
public class VacancyRestControllerV1 {

    @GetMapping("/vacancies")
    public ResponseEntity<?> getVacancy(@PathVariable Long userId) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
