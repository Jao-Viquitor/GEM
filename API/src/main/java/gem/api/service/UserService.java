package gem.api.service;

import gem.api.model.user.User;
import gem.api.model.user.UserCreateDTO;
import gem.api.model.user.UserDTO;
import gem.api.model.user.UserUpdateDTO;
import gem.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(UserCreateDTO json) {
        var data = new UserCreateDTO(json.username(), json.password(), json.email(), json.phone(), json.type(), json.nivel());
        return repository.save(new User(data));
    }

    public Page<UserDTO> getAllUsers(Pageable pag) {
        return repository.findAll(pag).map(UserDTO::new);
    }

    public void updateUser(UserUpdateDTO json) {
        User user = repository.getReferenceById(json.id());
        user.updateInfo(json);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
