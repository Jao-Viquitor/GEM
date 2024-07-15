package gem.api.controller;

import gem.api.model.admin.AdminDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AdminController {

    private final AuthenticationManager manager;

    public AdminController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AdminDTO data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(token);

        return ResponseEntity.ok(auth);
    }

}
