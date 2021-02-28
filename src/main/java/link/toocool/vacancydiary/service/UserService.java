package link.toocool.vacancydiary.service;

import link.toocool.vacancydiary.dto.user.EditUserDTO;
import link.toocool.vacancydiary.dto.user.RegisterUserDTO;
import link.toocool.vacancydiary.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUser(Long id);

    UserDTO createUser(RegisterUserDTO userDTO);

    UserDTO editUser(Long userId, EditUserDTO userDTO);

    UserDTO deleteUser(Long userId);

    List<UserDTO> getUsers();
}
