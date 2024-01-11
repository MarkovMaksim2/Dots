package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import web.controllers.requests.AuthenticationRequest;
import web.controllers.responses.AuthenticationResponse;
import web.dto.UserDto;
import web.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto request) {
        UserDto userDto = userService.createUser(request);

        if (userDto == null) return null;

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(UserMapper.mapToUser(userDto)))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getLogin(),
                  request.getPassword()
          )
        );

        UserDto userDto = userService.getUserByLogin(request.getLogin());

        if (userDto == null) return null;

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(UserMapper.mapToUser(userDto)))
                .build();
    }
}
