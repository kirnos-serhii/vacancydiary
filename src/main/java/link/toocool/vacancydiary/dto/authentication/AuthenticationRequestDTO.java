package link.toocool.vacancydiary.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

    private String email;

    private String password;
}
