package link.toocool.vacancydiary.service.impl;

import link.toocool.vacancydiary.mapper.UserMapper;
import link.toocool.vacancydiary.dto.user.EditUserDTO;
import link.toocool.vacancydiary.dto.user.CreateUserDTO;
import link.toocool.vacancydiary.dto.user.UserDTO;
import link.toocool.vacancydiary.entity.User;
import link.toocool.vacancydiary.exception.RestException;
import link.toocool.vacancydiary.repository.UserRepository;
import link.toocool.vacancydiary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RestException("No such user.", HttpStatus.NOT_FOUND));
        return userMapper.dtoFromUser(user);
    }

    @Override
    @Transactional
    public UserDTO createUser(CreateUserDTO userDTO) {
        User user = userMapper.userFromDto(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.dtoFromUser(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDTO editUser(Long userId, EditUserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RestException("", HttpStatus.NOT_FOUND));

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.dtoFromUser(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDTO deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RestException("", HttpStatus.NOT_FOUND));
        UserDTO userDTO = userMapper.dtoFromUser(userRepository.save(user));

        userRepository.delete(user);
        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::dtoFromUser)
                .collect(Collectors.toList());
    }
}
