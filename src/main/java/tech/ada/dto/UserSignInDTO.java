package tech.ada.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignInDTO (
        @NotBlank(message = "Email must not be blank")
//        @Email
        String email,

        @NotBlank(message = "Password must not be blank")
//        @Size(min = 8, message = "Password must have at least 8 characters")
        String password
) {
}
