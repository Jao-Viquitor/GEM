package gem.api.controller;

import gem.api.model.admin.Admin;
import gem.api.model.admin.AdminDTO;
import gem.api.model.admin.AdminTokenDTO;
import gem.api.service.AdminService;
import gem.api.service.TokenService;
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

    public final AdminService adminService;
    private final TokenService tokenService;
    private final AuthenticationManager manager;

    public AdminController(AdminService adminService, TokenService tokenService, AuthenticationManager manager) {
        this.adminService = adminService;
        this.tokenService = tokenService;
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AdminDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(authToken);
        var token = tokenService.getToken((Admin) auth.getPrincipal());

        return ResponseEntity.ok(new AdminTokenDTO(token));
    }

}
