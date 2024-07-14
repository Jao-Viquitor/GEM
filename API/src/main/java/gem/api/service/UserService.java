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

    public void saveUser(UserCreateDTO json) {
        repository.save(new User(json));
    }

    public Page<UserDTO> getAllUsers(Pageable pag) {
        return repository.findAll(pag).map(UserDTO::new);
    }

    public void updateUser(UserUpdateDTO json) {
        var medico = repository.getReferenceById(json.id());
        medico.updateInfo(json);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

}
