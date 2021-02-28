package link.toocool.vacancydiary.dto.validation;

import link.toocool.vacancydiary.dto.user.RegisterUserDTO;
import link.toocool.vacancydiary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UserEmailUniqueValidator implements ConstraintValidator<UserEmailUnique, RegisterUserDTO> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(RegisterUserDTO value, ConstraintValidatorContext context) {
        return userRepository.findByEmail(value.getEmail()).isEmpty();
    }
}
