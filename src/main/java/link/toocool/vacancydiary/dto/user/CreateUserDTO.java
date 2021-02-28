package link.toocool.vacancydiary.dto.user;

import link.toocool.vacancydiary.dto.validation.UserEmailUnique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@UserEmailUnique
public class CreateUserDTO {

    @Email(message = "Incorrect email address.")
    private String email;

    @NotEmpty(message = "Password should be specified.")
    private String password;
}
