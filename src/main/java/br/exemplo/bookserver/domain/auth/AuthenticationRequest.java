package br.exemplo.bookserver.domain.auth;

public record AuthenticationRequest(
        String email,
        String password
){
}
