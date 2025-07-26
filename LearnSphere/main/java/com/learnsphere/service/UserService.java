package com.learnsphere.service;

import com.learnsphere.model.User;
import com.learnsphere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now().toString());
        user.setUpdatedAt(LocalDateTime.now().toString());
        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    public User updateProfile(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getEmail().equals(updatedUser.getEmail()) && 
            userRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setBio(updatedUser.getBio());
        user.setLocation(updatedUser.getLocation());
        user.setWebsite(updatedUser.getWebsite());
        user.setUpdatedAt(LocalDateTime.now().toString());

        return userRepository.save(user);
    }

    public String updateProfilePicture(Long userId, MultipartFile file) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            String pictureUrl = "/uploads/" + fileName;
            user.setProfilePicture(pictureUrl);
            user.setUpdatedAt(LocalDateTime.now().toString());
            userRepository.save(user);

            return pictureUrl;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }
    }

    public void updatePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now().toString());
        userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    public User updateProfile(Long userId, Map<String, String> updates) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updates.containsKey("name")) {
            user.setName(updates.get("name"));
        }
        if (updates.containsKey("email")) {
            String newEmail = updates.get("email");
            if (!user.getEmail().equals(newEmail) && userRepository.findByEmail(newEmail).isPresent()) {
                throw new RuntimeException("Email already in use");
            }
            user.setEmail(newEmail);
        }
        if (updates.containsKey("bio")) {
            user.setBio(updates.get("bio"));
        }
        if (updates.containsKey("location")) {
            user.setLocation(updates.get("location"));
        }
        if (updates.containsKey("website")) {
            user.setWebsite(updates.get("website"));
        }

        user.setUpdatedAt(java.time.LocalDateTime.now().toString());
        return userRepository.save(user);
    }
 
    public void sendPasswordResetEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email not found");
        }

        System.out.println("Password reset requested for email: " + email);
    }

    
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now().toString());
        userRepository.save(user);
    }


}
