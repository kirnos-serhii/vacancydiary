package link.toocool.vacancydiary.dto.validation;

import link.toocool.vacancydiary.dto.user.CreateUserDTO;
import link.toocool.vacancydiary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UserEmailUniqueValidator implements ConstraintValidator<UserEmailUnique, CreateUserDTO> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(CreateUserDTO value, ConstraintValidatorContext context) {
        return userRepository.findByEmail(value.getEmail()).isEmpty();
    }
}
