package gem.api.model.user;

import gem.api.model.enums.Nivel;
import gem.api.model.enums.UserType;
import jakarta.persistence.*;
import lombok.*;


import java.sql.Timestamp;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    private Timestamp created;

    private Timestamp update_at;

    public User(UserCreateDTO data) {
        this.username = data.username();
        this.password = data.password();
        this.email = data.email();
        this.phone = data.phone();
        this.type = data.type();
        this.nivel = data.nivel();
    }

    public void updateInfo(UserUpdateDTO data) {
        Optional.ofNullable(data.username()).ifPresent(this::setUsername);
        Optional.ofNullable(data.password()).ifPresent(this::setPassword);
        Optional.ofNullable(data.email()).ifPresent(this::setEmail);
        Optional.ofNullable(data.phone()).ifPresent(this::setPhone);
        Optional.ofNullable(data.type()).ifPresent(this::setType);
        Optional.ofNullable(data.nivel()).ifPresent(this::setNivel);
    }

}
