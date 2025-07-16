package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.UserRequestDTO;
import com.diorama.shop.dto.client.response.UserResponseDTO;
import com.diorama.shop.exception.UserAlreadyExistsException;
import com.diorama.shop.exception.UserNotFoundException;
import com.diorama.shop.mapper.UserMapper;
import com.diorama.shop.model.*;
import com.diorama.shop.repository.*;
import com.diorama.shop.util.EmailTemplateUtil;
import com.diorama.shop.util.UserConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private EmailService emailService;

    @Value("${app.frontend.url}")
    private String frontendUrl;


    public UserResponseDTO register(UserRequestDTO dto) {        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException(UserConstants.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmailAndIsActiveTrue(dto.getEmail())) {
            throw new UserAlreadyExistsException(UserConstants.EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByPhoneAndIsActiveTrue(dto.getPhone())) {
            throw new UserAlreadyExistsException(UserConstants.PHONE_ALREADY_EXISTS);
        }
    
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setAvatarUrl(dto.getAvatarUrl());
    
        Role userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(Set.of(userRole));
    
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setIsEmailVerified(false);
    
        User savedUser = userRepository.save(user);
    
        // Gửi email xác thực
        String verificationUrl = frontendUrl + "/verify?token=" + token;
        Map<String, String> placeholders = Map.of("verificationUrl", verificationUrl);
        String htmlContent = EmailTemplateUtil.loadTemplate("email_verification.html", placeholders);

        emailService.sendEmail(user.getEmail(), "Xác minh địa chỉ email của bạn", htmlContent);

    
        return UserMapper.toResponseDTO(savedUser);
    }
    

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toResponseDTO).toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
        return UserMapper.toResponseDTO(user);
    }

    public UserResponseDTO updateCurrentUser(UserRequestDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
        
        // Update user fields
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getAvatarUrl() != null) {
            user.setAvatarUrl(dto.getAvatarUrl());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmailAndIsActiveTrue(dto.getEmail())) {
                throw new UserAlreadyExistsException(UserConstants.EMAIL_ALREADY_EXISTS);
            }
            user.setEmail(dto.getEmail());
        }
        
        return UserMapper.toResponseDTO(userRepository.save(user));
    }
    
        public boolean toggleUserActiveStatus(Long id) {
            User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
        
            boolean newStatus = !user.isActive(); // gạt trạng thái
            user.setIsActive(newStatus);
            userRepository.save(user);
            return newStatus; // trả về để biết giờ đang là true/false
        }

        public boolean toggleCurrentUserActiveStatus() {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
        
            boolean newStatus = !user.isActive();
            user.setIsActive(newStatus);
            userRepository.save(user);
            return newStatus;
        }
        
        

    public void deactivateSelf() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(UserConstants.USER_NOT_FOUND));
        user.setIsActive(false);
        userRepository.save(user);
    }

}