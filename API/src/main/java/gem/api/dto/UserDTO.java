package gem.api.dto;

import gem.api.model.User;
import gem.api.model.enums.Nivel;
import gem.api.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record UserDTO (String username, String password, String email, String phone, UserType type, Nivel nivel, LocalDateTime created, LocalDateTime updated) {
    private static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getType(),
                user.getNivel(),
                user.getCreated().toLocalDateTime(),
                user.getUpdate().toLocalDateTime()
        );
    }
}