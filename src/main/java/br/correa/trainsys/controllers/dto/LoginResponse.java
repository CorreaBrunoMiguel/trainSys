package br.correa.trainsys.controllers.dto;


public record LoginResponse(
        String acessToken,
        Long expireTime,
        String message
) {
}
