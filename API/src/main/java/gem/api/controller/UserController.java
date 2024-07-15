package gem.api.controller;

import gem.api.model.user.UserCreateDTO;
import gem.api.model.user.UserDTO;
import gem.api.model.user.UserUpdateDTO;
import gem.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> index(@PageableDefault(sort = {"username"}) Pageable pag){
        return service.getAllUsers(pag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> store(@Valid @RequestBody UserCreateDTO json, UriComponentsBuilder uriBuilder){
        return service.saveUser(json, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserUpdateDTO json){
        return service.updateUser(json);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        return service.deleteUser(id);
    }

}
