package gem.api.model.user;

import gem.api.model.enums.Nivel;
import gem.api.model.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @NotNull
        Long id,
        @Size(min = 3, max = 255)
        String username,
        @Size(min = 8)
        String password,
        @Email
        @Size(max = 255)
        String email,
        @Size(min = 8, max = 12)
        String phone,
        UserType type,
        Nivel nivel
) {
}
