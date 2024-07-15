package gem.api.service;

import gem.api.common.enums.HttpCode;
import gem.api.model.user.User;
import gem.api.model.user.UserCreateDTO;
import gem.api.model.user.UserDTO;
import gem.api.model.user.UserUpdateDTO;
import gem.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pag) {
        var data = repository.findAll(pag);
        return ResponseEntity.status(HttpCode.OK.getValue()).body(data.map(UserDTO::new));
    }

    public ResponseEntity<UserDTO> getUserById(Long id) {
        var user = repository.getReferenceById(id);
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getType(), user.getNivel()));
    }

    public ResponseEntity<UserDTO> saveUser(UserCreateDTO json, UriComponentsBuilder uriBuilder) {
        var data = new UserCreateDTO(json.username(), json.password(), json.email(), json.phone(), json.type(), json.nivel());
        User user = repository.save(new User(data));
        var uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getType(), user.getNivel()));
    }

    public ResponseEntity<UserDTO> updateUser(UserUpdateDTO json) {
        User user = repository.getReferenceById(json.id());
        user.updateInfo(json);
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getType(), user.getNivel()));
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
