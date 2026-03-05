package com.sarthak.workflow.auth;

import com.sarthak.workflow.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        if (!"admin".equals(request.username()) ||
                !"admin123".equals(request.password())) {

            return ResponseEntity.status(401).build();
        }

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setEmail("admin@test.com");

        return ResponseEntity.ok(
                new LoginResponse("dev-token", user)
        );
    }
}