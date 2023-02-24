package br.exemplo.bookserver.domain.auth;

import br.exemplo.bookserver.domain.user.User;
import br.exemplo.bookserver.domain.user.UserRepository;
import br.exemplo.bookserver.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService tokenService;
    @Autowired
    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.firstName())
                .lastname(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = tokenService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authentication = manager.authenticate(authToken);

        var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return new AuthenticationResponse(jwtToken);
    }
}
