package br.com.fincore.security;

import br.com.fincore.security.domain.model.UserSite;
import br.com.fincore.security.domain.request.UserDTO;
import br.com.fincore.security.service.TokenService;
import br.com.fincore.security.service.UserAuthService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.Objects;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserAuthService userAuthService;
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(UserAuthService userAuthService, ObjectMapper objectMapper, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userAuthService = userAuthService;
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/v1.0/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO){
        var userId = userAuthService.registerUser(userDTO);
        if (userId > 0) {
            var response = objectMapper.createObjectNode();
            response.put("userId:", userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists");
    }

    @PostMapping("/v1.0/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                        @RequestParam String password) {
        try {
            var cryptoUserNamePassword = new UsernamePasswordAuthenticationToken(email.toLowerCase(), password);
            var auth = authenticationManager.authenticate(cryptoUserNamePassword);
            var token = tokenService.tokenGenerate(Objects.requireNonNull(auth.getPrincipal()).toString());
            var response = objectMapper.createObjectNode();

            response.put("token", token);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                    .body("Error when logging in! ERROR: " + e.getMessage());
        }
    }
}