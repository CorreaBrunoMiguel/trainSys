package br.correa.trainsys.controllers.dto;

public record LoginRequest(
        String email,
        String password
) {
}
