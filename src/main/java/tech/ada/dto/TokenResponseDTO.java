package tech.ada.dto;

public record TokenResponseDTO (
        String token,
        long expiresIn
) {
}
