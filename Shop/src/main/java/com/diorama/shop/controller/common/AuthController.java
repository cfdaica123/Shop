package com.diorama.shop.controller.common;

import com.diorama.shop.dto.client.request.UserRequestDTO;
import com.diorama.shop.dto.client.response.UserResponseDTO;
import com.diorama.shop.dto.common.request.AuthRequestDTO;
import com.diorama.shop.dto.common.response.*;

import com.diorama.shop.model.User;
import com.diorama.shop.repository.UserRepository;
import com.diorama.shop.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.diorama.shop.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        Optional<User> userOpt = userRepository.findByVerificationToken(token);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid token");

        User user = userOpt.get();
        user.setIsEmailVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponseDTO(token, dto.getUsername()));
    }


}
