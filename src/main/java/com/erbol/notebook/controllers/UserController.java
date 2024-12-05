package com.erbol.notebook.controllers;


import com.erbol.notebook.entities.User;
import com.erbol.notebook.utils.JwtUtil;
import com.erbol.notebook.config.SecurityConfig;
import com.erbol.notebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil = new JwtUtil();
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Fetch the user by email
        User existingUser = userService.getUserByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Validate the password
        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            // Generate JWT token if password matches
            String token = jwtUtil.generateToken(existingUser.getEmail());
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }



}
