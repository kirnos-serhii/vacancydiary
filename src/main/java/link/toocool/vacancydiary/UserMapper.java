package link.toocool.vacancydiary;

import link.toocool.vacancydiary.dto.user.RegisterUserDTO;
import link.toocool.vacancydiary.dto.user.UserDTO;
import link.toocool.vacancydiary.entity.Role;
import link.toocool.vacancydiary.entity.Status;
import link.toocool.vacancydiary.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User userFromDto(RegisterUserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public UserDTO dtoFromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }
}
