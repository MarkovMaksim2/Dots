package web.service;

import web.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByLogin(String userLogin);
}
