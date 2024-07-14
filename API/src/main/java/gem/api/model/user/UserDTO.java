package gem.api.model.user;

import gem.api.model.enums.Nivel;
import gem.api.model.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UserDTO (
        Long id,
        @NotBlank
        @Size(min = 3, max = 255)
        String username,
        @NotBlank
        @Email
        @Size(max = 255)
        String email,
        @NotBlank
        @Size(min = 8, max = 12)
        String phone,
        @NotNull
        UserType type,
        @NotNull
        Nivel nivel) {

    public UserDTO(User user) {
       this(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getType(), user.getNivel());
    }
}