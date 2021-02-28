package link.toocool.vacancydiary.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UserEmailUniqueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEmailUnique {

    String message() default "User's email should be unique.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
