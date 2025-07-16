package com.diorama.shop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diorama.shop.dto.client.request.UserRequestDTO;
import com.diorama.shop.dto.client.response.UserResponseDTO;
import com.diorama.shop.service.UserService;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('USER')")
public class UserClientController {

    @Autowired private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateCurrentUser(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateCurrentUser(dto));
    }

    @PutMapping("/me/toggle-active")
    public ResponseEntity<?> toggleOwnActiveStatus() {
        boolean isActiveNow = userService.toggleCurrentUserActiveStatus();
        String message = isActiveNow ? "User activated" : "User deactivated";
        return ResponseEntity.ok(message);
    }

}

