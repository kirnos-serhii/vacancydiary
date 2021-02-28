package link.toocool.vacancydiary.dto.user;

import link.toocool.vacancydiary.entity.Role;
import link.toocool.vacancydiary.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long Id;

    private String email;

    private String password;

    private Role role;

    private Status status;
}
