package br.exemplo.bookserver.domain.auth;

public record RegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
