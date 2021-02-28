package link.toocool.vacancydiary.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDTO {

    @NotEmpty(message = "Password should be specified.")
    private String password;
}
