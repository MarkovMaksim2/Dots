package web.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dto.UserDto;
import web.entities.User;
import web.mapper.UserMapper;
import web.repository.UserRepository;
import web.service.UserService;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        Optional<User> check = userRepository.findByLogin(user.getLogin());

        if (check.isPresent()) return null;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);

        return user.map(UserMapper::mapToUserDto).orElse(null);
    }
}
