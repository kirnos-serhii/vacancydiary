package link.toocool.vacancydiary.controllers.rest;

import link.toocool.vacancydiary.dto.user.EditUserDTO;
import link.toocool.vacancydiary.dto.user.CreateUserDTO;
import link.toocool.vacancydiary.service.UserService;
import link.toocool.vacancydiary.util.ControllerUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserRestControllerV1 {

    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO userDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ControllerUtil.getErrorMessages(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUserDTO userDTO,
                                      BindingResult bindingResult,
                                      @PathVariable Long userId) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ControllerUtil.getErrorMessages(bindingResult));
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.editUser(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(userId));
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }
}
