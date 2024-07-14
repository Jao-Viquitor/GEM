package gem.api.controller;

import gem.api.model.user.User;
import gem.api.model.user.UserCreateDTO;
import gem.api.model.user.UserDTO;
import gem.api.model.user.UserUpdateDTO;
import gem.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void createUser(@Valid @RequestBody UserCreateDTO json){
        repository.save(new User(json));
    }

    @GetMapping
    public Page<UserDTO> getAllUsers(@PageableDefault(sort = {"username"}) Pageable pag){
        return repository.findAll(pag).map(UserDTO::new);
    }

    @PutMapping
    @Transactional
    public void updateUser(@Valid @RequestBody UserUpdateDTO json){
        var medico = repository.getReferenceById(json.id());
        medico.updateInfo(json);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable long id){
        repository.deleteById(id);
    }

}
