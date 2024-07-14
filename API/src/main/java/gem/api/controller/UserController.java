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

    @PostMapping
    @Transactional
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO json, UriComponentsBuilder uriBuilder){
        var user = service.saveUser(json);
        var uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPhone(), user.getType(), user.getNivel()));
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@PageableDefault(sort = {"username"}) Pageable pag){
        return ResponseEntity.ok(service.getAllUsers(pag));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserUpdateDTO json){
        service.updateUser(json);
        return ResponseEntity.ok(new UserDTO(json.id(), json.username(), json.email(), json.phone(), json.type(), json.nivel()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
