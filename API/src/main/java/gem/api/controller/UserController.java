package gem.api.controller;

import gem.api.model.user.UserCreateDTO;
import gem.api.model.user.UserDTO;
import gem.api.model.user.UserUpdateDTO;
import gem.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public void createUser(@Valid @RequestBody UserCreateDTO json){
        service.saveUser(json);
    }

    @GetMapping
    public Page<UserDTO> getAllUsers(@PageableDefault(sort = {"username"}) Pageable pag){
        return service.getAllUsers(pag);
    }

    @PutMapping
    @Transactional
    public void updateUser(@Valid @RequestBody UserUpdateDTO json){
        service.updateUser(json);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

}
